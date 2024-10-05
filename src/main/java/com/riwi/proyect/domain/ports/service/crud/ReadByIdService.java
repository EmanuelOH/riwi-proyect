package com.riwi.proyect.domain.ports.service.crud;

public interface ReadByIdService<Entity, ID> {
    public Entity readById(ID id);
}
