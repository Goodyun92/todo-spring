package com.example.todo.controller;

import com.example.todo.model.User;
import com.example.todo.model.dto.UserDto;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

//    @Autowired
    private final UserService userService;

    @CrossOrigin("*")
    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody UserDto userDto) {
        User savedUser = userService.signUp(userDto);
        return ResponseEntity.ok(savedUser);
    }

    @CrossOrigin("*")
    @GetMapping("/log-in")
    public ResponseEntity<Long> logIn(@RequestParam String userName, @RequestParam String password) {
        Optional<Long> userId = userService.logIn(userName, password);
        return userId.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @CrossOrigin("*")
    @PostMapping("/{user_id}/log-out")
    public ResponseEntity<Void> logOut(@PathVariable("user_id") String userId) {
        userService.logOut(userId);
        return ResponseEntity.ok().build();
    }
}

