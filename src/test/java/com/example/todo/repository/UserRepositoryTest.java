package com.example.todo.repository;

import com.example.todo.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("testUsername");
        user.setPassword("testPassword");

        User savedUser = userRepository.save(user);

//        assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUserName()).isEqualTo("testUsername"); // Assuming that getId() gets the generated ID
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUserName("testUsername");
        user.setPassword("testPassword");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUserName("testUsername");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUserName()).isEqualTo("testUsername");
    }
}