package com.riwi.proyect.domain.ports.service.crud;

public interface CreateService<EntityRequest, Entity> {
    public Entity create (EntityRequest request);
}
