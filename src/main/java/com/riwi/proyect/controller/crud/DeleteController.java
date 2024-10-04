package com.riwi.proyect.controller.crud;

import org.springframework.http.ResponseEntity;

public interface DeleteController <ID>{
    public ResponseEntity<Void> delete(ID id);
}
