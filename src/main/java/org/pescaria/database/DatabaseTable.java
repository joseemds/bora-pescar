package org.pescaria.database;

import org.pescaria.entity.Entity;
import org.pescaria.exception.DatabaseException;
import org.pescaria.exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class DatabaseTable<T extends Entity> implements DatabaseTableI<T> {
    private Map<Integer, T> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public void save(T entity) throws DatabaseException {
        entity.id = currentId++;
        data.put(entity.getId(), entity);
    }

    @Override
    public T findById(int id) throws EntityNotFoundException {
        if (!data.containsKey(id)) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
        return data.get(id);
    }

    @Override
    public void update(T entity) throws EntityNotFoundException {
        if (!data.containsKey(entity.getId())) {
            throw new EntityNotFoundException("Entity with id " + entity.getId() + " not found");
        }
        data.put(entity.getId(), entity);
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        if (!data.containsKey(id)) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        }
        data.remove(id);
    }

    @Override
    public List<T> findAll() throws DatabaseException {
        return new ArrayList<>(data.values());
    }
}
