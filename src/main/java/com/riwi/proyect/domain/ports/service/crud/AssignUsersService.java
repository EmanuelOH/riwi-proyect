package com.riwi.proyect.domain.ports.service.crud;

import java.util.List;

public interface AssignUsersService<Entity,IdEntity, IdUser>{
    public Entity assignUser(IdEntity idEntity, List<IdUser> idUsers);
}
