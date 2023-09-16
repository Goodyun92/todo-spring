package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.model.dto.UserDto;
import com.example.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User signUp(UserDto userDto) {

        //중복처리
        Optional<User> existingUser = userRepository.findByUserName(userDto.getUserName());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
//        newUser.setUserName(request.getUserName());
//        newUser.setPassword(request.getPassword());

        return userRepository.save(newUser);
    }

    public Optional<Long> logIn(String userName, String password) {
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent() && user.get().getPassword().equals(password)) {
            return Optional.of(user.get().getUserId());
        }
        return Optional.empty();
    }

    public void logOut(String userId) {
        // 로그아웃 로직
    }
}
