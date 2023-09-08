package com.example.todo.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUserName() {
        User user = new User();
        user.setUserName("hdy");
        user.setPassword("hdypw");

        Assertions.assertThat(user.getUserName()).isEqualTo("hdy");
    }
}