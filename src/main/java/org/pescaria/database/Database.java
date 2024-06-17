package org.pescaria.database;

import org.pescaria.entity.*;
import org.pescaria.exception.DatabaseException;
import org.pescaria.exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Database instance;
    private Map<Class<?>, DatabaseTableI<? extends Entity>> tables = new HashMap<>();

    private Database() {
        tables.put(Pescaria.class, new DatabaseTable<Pescaria>());
        tables.put(Peixe.class, new DatabaseTable<Peixe>());
        tables.put(Usuario.class, new DatabaseTable<Usuario>());

        // admin mokado
        try {
            Usuario admin = new Usuario("dudu", "cachorro", TipoUsuario.ADMIN);
            save(admin);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public <T extends Entity> void save(T entity) throws DatabaseException {
        DatabaseTableI<T> table = getTable((Class<T>) entity.getClass());
        table.save(entity);
    }

    public <T extends Entity> T findById(Class<T> clazz, int id) throws EntityNotFoundException {
        DatabaseTableI<T> table = getTable(clazz);
        return table.findById(id);
    }

    public <T extends Entity> void update(T entity) throws EntityNotFoundException {
        DatabaseTableI<T> table = getTable((Class<T>) entity.getClass());
        table.update(entity);
    }

    public <T extends Entity> void delete(Class<T> clazz, int id) throws EntityNotFoundException {
        DatabaseTableI<T> table = getTable(clazz);
        table.delete(id);
    }

    public <T extends Entity> List<T> findAll(Class<T> clazz) throws DatabaseException {
        DatabaseTableI<T> table = getTable(clazz);
        return table.findAll();
    }

    private <T extends Entity> DatabaseTableI<T> getTable(Class<T> clazz) {
        return (DatabaseTableI<T>) tables.get(clazz);
    }
}
