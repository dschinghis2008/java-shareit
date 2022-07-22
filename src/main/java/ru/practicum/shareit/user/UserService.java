package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getById(Integer id){
        return userRepository.getById(id);
    }

    public Collection<User> getAll(){
        return userRepository.getAll();
    }

    public User add(User user){
        return userRepository.add(user);
    }

    public User update(User user,Integer id){
        return userRepository.update(user, id);
    }

    public void delete(Integer id){
        userRepository.delete(id);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }
}
