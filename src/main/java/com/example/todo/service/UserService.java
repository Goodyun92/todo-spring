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
        // 회원가입 로직 (데이터 유효성 검사, 암호화 등)
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
