package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UserRepositoryInMemory implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer idUser = 0;

    private Integer getNewId() {
        return ++idUser;
    }

    @Override
    public User getById(Integer id) {
        log.info("запрошен пользователь id={}, user=/{}", id, users.get(id).toString());
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        log.info("запрошены все пользователи");
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
        log.info("добавлен пользователь id={}", user.getId());
        return user;
    }

    @Override
    public User update(User user, Integer id) {

        if (users.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try {
            if (!user.getEmail().isEmpty() && user.getEmail() != null && !user.getEmail().equals("")) {
                for (User userTest : users.values()) {
                    if (user.getEmail().equals(userTest.getEmail())) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT);
                    }
                }
                users.get(id).setEmail(user.getEmail());
            }
        } catch (NullPointerException e) {
            log.info("email not found");
        }

        try {
            if (!user.getName().isEmpty() && user.getName() != null && !user.getName().equals("")) {
                users.get(id).setName(user.getName());
            }
        } catch (NullPointerException e) {
            log.info("name not found ");
        }
        log.info("обновлен пользователь id={}, user= /{}", id, users.get(id).toString());
        return users.get(id);
    }

    @Override
    public void delete(Integer id) {
        if (users.get(id) != null) {
            users.remove(id);
            log.info("удален пользователь id={}", id);
        }
    }

    @Override
    public void deleteAll() {
        users.clear();
        log.info("удалены все пользователи");
    }

    @Override
    public Boolean isPresent(Integer id) {
        if(users.get(id) == null){
            return false;
        } else {
            return true;
        }

    }
}
