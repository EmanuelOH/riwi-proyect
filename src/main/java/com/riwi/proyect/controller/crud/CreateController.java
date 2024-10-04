package com.riwi.proyect.controller.crud;

import org.springframework.http.ResponseEntity;

public interface CreateController <EntityRequest, Entity>{
    public ResponseEntity<Entity> create (EntityRequest request);
}
