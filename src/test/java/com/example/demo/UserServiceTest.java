package com.example.demo;

import com.example.demo.Models.New_car;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Captor
    private ArgumentCaptor<User> captor;

    private User user1, user2, user3;

    @BeforeEach
    void init() {
        user1 = new User();
        user1.setId(1);
        user1.setName("user1");
        user1.setPassword("password1");
        user1.setEmail("email1");
        user1.setType("user");

        user2 = new User();
        user2.setId(2);
        user2.setName("user2");
        user2.setPassword("password2");
        user2.setEmail("email2");
        user2.setType("user");

        user3 = new User();
        user3.setId(3);
        user3.setName("user3");
        user3.setPassword("password3");
        user3.setEmail("email3");
        user3.setType("user");
    }

    @Test
    void create() {
        userService.create("user4", "password4", "email4", "user");
        Mockito.verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        assertEquals("email4", captured.getEmail());
    }

    @Test
    void saveChanges(){
        user2.setEmail("email321");
        userService.saveChanges(user2);
        assertEquals("email321", user2.getEmail());
    }

    @Test
    void findByName() {
        Mockito.when(userService.findByName("user2")).thenReturn(user2);
        assertEquals(user2, userRepository.findByName("user2"));
    }

    @Test
    void readAll() {
        ArrayList<User> user = new ArrayList<>();
        user.add(user1);
        user.add(user2);
        user.add(user3);
        Mockito.when(userRepository.findAll()).thenReturn(user);
        assertEquals(user, userRepository.findAll());
    }

    @Test
    void deleteUser() {
        userService.deleteUser("user1");
        Mockito.verify(userRepository).deleteByName("user1");
    }
}
