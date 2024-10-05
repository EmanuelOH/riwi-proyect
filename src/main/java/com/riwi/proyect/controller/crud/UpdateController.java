package com.riwi.proyect.controller.crud;

import org.springframework.http.ResponseEntity;

public interface UpdateController <EntityRequest, Entity, ID>{
    public ResponseEntity<Entity> update(EntityRequest request, ID id);
}
