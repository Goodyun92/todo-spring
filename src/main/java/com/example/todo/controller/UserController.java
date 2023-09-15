package com.example.todo.controller;

import com.example.todo.model.User;
import com.example.todo.model.dto.UserDto;
import com.example.todo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "users API Document")
public class UserController {

//    @Autowired
    private final UserService userService;

    @CrossOrigin("*")
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "회원가입을 수행합니다.")
    public ResponseEntity<User> signUp(@RequestBody UserDto userDto) {
        User savedUser = userService.signUp(userDto);
        return ResponseEntity.ok(savedUser);
    }

    @CrossOrigin("*")
    @PostMapping("/{user_id}/log-out")
    @Operation(summary = "로그아웃", description = "로그아웃을 수행합니다.")
    public ResponseEntity<Void> logOut(@PathVariable("user_id") String userId) {
        userService.logOut(userId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin("*")
    @GetMapping("/log-in")
    @Operation(summary = "로그인", description = "로그인을 수행합니다.")
    public ResponseEntity<Long> logIn(@RequestParam String userName, @RequestParam String password) {
        Optional<Long> userId = userService.logIn(userName, password);
        return userId.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}

