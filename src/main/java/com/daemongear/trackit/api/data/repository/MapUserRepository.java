package com.daemongear.trackit.api.data.repository;

import com.daemongear.trackit.api.data.entity.User;

import java.util.*;

/**
 * Created by robertoosorio on 15-08-16.
 */
public class MapUserRepository implements UserRepository {

    private final Map<Long, User> dataBase = new HashMap<>();

    @Override
    public User findOne(Long id) {
        if (dataBase.containsKey(id)) {
            return dataBase.get(id).getRemovedAt() == null ? dataBase.get(id) : null;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        dataBase.forEach((aLong, user) -> {
            if (user.getRemovedAt() == null) {
                users.add(user);
            }
        });
        return users;
    }

    @Override
    public Long save(User entity) {
        if (entity.getId() == null) {
            return create(entity);
        } else {
            if (dataBase.containsKey(entity.getId())) {
                return update(entity);
            } else {
                return create(entity);
            }
        }
    }

    @Override
    public Long create(User entity) {
        entity.setId(dataBase.size() +1L);
        entity.setCreatedAt(new Date());
        dataBase.put(entity.getId(), entity);
        return entity.getId();
    }

    @Override
    public Long update(User entity) {
        if (entity.getId() != null) {
            if (dataBase.containsKey(entity.getId())) {
                entity.setUpdatedAt(new Date());
                dataBase.replace(entity.getId(), entity);
                return entity.getId();
            }
        }

        return null;
    }

    @Override
    public void delete(Long id, boolean soft) {
        if (id != null) {
            if (soft) {
                User storedUser = dataBase.get(id);
                storedUser.setRemovedAt(new Date());
                dataBase.replace(id, storedUser);
            } else {
                dataBase.remove(id);
            }
        }
    }
}
