package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * // TODO .
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userServiceImpl.getById(id);
    }

    @GetMapping
    public Collection<User> getAll() {
        return userServiceImpl.getAll();
    }

    @PostMapping
    public User add(@Valid @RequestBody User user) {
        return userServiceImpl.add(user);
    }

    @PatchMapping("/{id}")
    public User update(@RequestBody User user,@PathVariable Integer id) {
        return userServiceImpl.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userServiceImpl.delete(id);
    }

    @DeleteMapping
    public void deleteAll() {
        userServiceImpl.deleteAll();
    }

}
