package com.riwi.proyect.application.service;

import com.riwi.proyect.application.dtos.requests.ProjectRequestDto;
import com.riwi.proyect.application.dtos.responses.ProjectResponseDto;
import com.riwi.proyect.application.mappers.ProjectMapper;
import com.riwi.proyect.application.mappers.TaskMapper;
import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.entities.Tasks;
import com.riwi.proyect.domain.entities.Users;
import com.riwi.proyect.domain.enums.RoleEnum;
import com.riwi.proyect.domain.ports.service.interfaces.IProjectService;
import com.riwi.proyect.infrastructure.helpers.JwtUtil;
import com.riwi.proyect.infrastructure.persistence.ProjectRepository;
import com.riwi.proyect.infrastructure.persistence.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Project create(ProjectRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long creatorUserId = ((Users) userDetails).getId();

        Project project = ProjectMapper.INSTANCE.toEntity(requestDto);

        Users user = userRepository.findById(creatorUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (project.getUsers() == null) {
            project.setUsers(new HashSet<>());
        }

        project.getUsers().add(user);

        if (requestDto.getTasks() != null) {
            List<Tasks> taskEntities = requestDto.getTasks().stream()
                    .map(taskDto -> {
                        Tasks task = TaskMapper.INSTANCE.toEntity(taskDto);
                        task.setProject(project);
                        return task;
                    })
                    .collect(Collectors.toList());

            project.setTasks(taskEntities);
        }

        return this.projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        if(!projectRepository.existsById(id)){
            throw new EntityNotFoundException("Project not found with id: " + id);
        }

        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectResponseDto> readAll() {
        List<Project> projectList = projectRepository.findAll();

        return projectList.stream()
                .map(ProjectMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto readById(Long id) {
        Project project = projectRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        return ProjectMapper.INSTANCE.toResponseDto(project);
    }

    @Override
    public Project update(ProjectRequestDto requestDto, Long id) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    Project project = ProjectMapper.INSTANCE.toEntity(requestDto);
                    project.setId(existingProject.getId());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    }

    @Override
    public Project assignUser(Long projectId, List<Long> userIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + "not found"));

        Set<Users> users = userIds.stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + "not found"))
                ).collect(Collectors.toSet());

        project.getUsers().addAll(users);

        users.stream()
                .map(Users::getEmail)
                .forEach(email -> sendAssignProjectMessageEmail(project, email));

        return projectRepository.save(project);
    }

    @Override
    public List<ProjectResponseDto> getProject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users currentUser = (Users) userDetails;

        List<Project> projectList;

        if (currentUser.getRole().equals(RoleEnum.ADMIN)) {
            projectList = projectRepository.findAll();
        } else {
            projectList = projectRepository.findByUsersContains(currentUser);
        }

        return projectList.stream()
                .map(ProjectMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    private void sendAssignProjectMessageEmail(Project project, String userEmail){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(userEmail);
        message.setSubject("Nuevo proyecto creado: " + project.getName());
        message.setText("Hola,\n\nSe ha creado un nuevo proyecto llamado: " + project.getName() +
                "\n\nDetalles del proyecto:\n" + "ID: " + project.getId() +
                "\n\nSaludos,\nTu equipo de soporte.");

        emailSender.send(message);
    }
}
