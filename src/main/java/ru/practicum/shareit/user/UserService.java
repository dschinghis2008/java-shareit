package ru.practicum.shareit.user;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    User getById(Integer id);

    Collection<User> getAll();

    User add(User user);

    User update(User user, Integer id);

    void delete(Integer id);

    void deleteAll();
}
