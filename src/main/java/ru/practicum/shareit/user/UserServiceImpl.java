package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(Integer id){
        return userRepository.getById(id);
    }

    @Override
    public Collection<User> getAll(){
        return userRepository.getAll();
    }

    @Override
    public User add(User user){
        return userRepository.add(user);
    }

    @Override
    public User update(User user,Integer id){
        return userRepository.update(user, id);
    }

    @Override
    public void delete(Integer id){
        userRepository.delete(id);
    }

    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }
}
