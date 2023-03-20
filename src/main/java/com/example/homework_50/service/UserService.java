package com.example.homework_50.service;



import com.example.homework_50.dao.UserDao;
import com.example.homework_50.dto.UserDto;
import com.example.homework_50.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public UserDto findByEmail(String email){
        var user = userDao.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the email: " + email));
        return UserDto.from(user);
    }

    public UserDto findByName(String name){
        var user = userDao.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the name: " + name));
        return UserDto.from(user);
    }

    public UserDto findByUserName(String username){
        var user = userDao.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the username: " + username));
        return UserDto.from(user);
    }
}
