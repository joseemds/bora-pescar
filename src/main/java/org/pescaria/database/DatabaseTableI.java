package org.pescaria.database;

import org.pescaria.entity.Entity;
import org.pescaria.exception.DatabaseException;
import org.pescaria.exception.EntityNotFoundException;
import java.util.List;

public interface DatabaseTableI<T extends Entity> {
    void save(T entity) throws DatabaseException;

    T findById(int id) throws EntityNotFoundException;

    void update(T entity) throws EntityNotFoundException;

    void delete(int id) throws EntityNotFoundException;

    List<T> findAll() throws DatabaseException;
}
