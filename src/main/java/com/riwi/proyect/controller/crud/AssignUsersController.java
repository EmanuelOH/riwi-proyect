package com.riwi.proyect.controller.crud;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignUsersController<Entity ,IdEntity, IdUser>{
    public ResponseEntity<Entity> assignUser(IdEntity idEntity, List<IdUser> idUsers);
}
