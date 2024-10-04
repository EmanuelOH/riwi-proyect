package com.riwi.proyect.controller.crud;

import org.springframework.http.ResponseEntity;

public interface ReadByIdController <Entity, ID>{
    public ResponseEntity<Entity> readById(ID id);
}
