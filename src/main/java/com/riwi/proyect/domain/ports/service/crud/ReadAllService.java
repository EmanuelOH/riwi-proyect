package com.riwi.proyect.domain.ports.service.crud;

import java.util.List;

public interface ReadAllService<Entity> {
    public List<Entity> readAll();
}
