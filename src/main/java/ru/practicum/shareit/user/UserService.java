package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getById(Integer id){
        log.info("запрошен пользователь id={}, user=/{}",id,userRepository.getById(id).toString());
        return userRepository.getById(id);
    }

    public Collection<User> getAll(){
        log.info("запрошены все пользователи");
        return userRepository.getAll();
    }

    public User add(User user){
        log.info("добавлен пользователь id={}",user.getId());
        return userRepository.add(user);
    }

    public User update(User user,Integer id){
        log.info("обновлен пользователь id={}, user= /{}",id,user.toString());
        return userRepository.update(user, id);
    }

    public void delete(Integer id){
        userRepository.delete(id);
        log.info("удален пользователь id={}",id);
    }

    public void deleteAll(){
        userRepository.deleteAll();
        log.info("удалены все пользователи");
    }
}
