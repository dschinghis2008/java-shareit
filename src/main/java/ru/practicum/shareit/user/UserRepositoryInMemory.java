package ru.practicum.shareit.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryInMemory implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer idUser = 0;

    private Integer getNewId() {
        return ++idUser;
    }

    @Override
    public User getById(Integer id) {
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }

    @Override
    public User add(User user) {
        for (User userTest : users.values()) {
            if (user.getEmail().equals(userTest.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
        user.setId(getNewId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user, Integer id) {
        if (users.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!user.getEmail().isEmpty() && user.getEmail() != null) {
            users.get(id).setEmail(user.getEmail());
        }

        if (!user.getName().isEmpty() && user.getName() != null) {
            users.get(id).setName(user.getName());
        }

        return users.get(id);
    }

    @Override
    public void delete(Integer id) {
        if (users.get(id) != null) {
            users.remove(id);
        }
    }

    @Override
    public void deleteAll() {
        users.clear();
    }
}
