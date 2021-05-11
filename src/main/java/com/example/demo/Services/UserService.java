package com.example.demo.Services;

import lombok.RequiredArgsConstructor;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findByName(s);
        return u;
    }

    public void create(String name, String pass, String email, String type) {
        User u = new User();
        u.setName(name);
        u.setPassword(bCryptPasswordEncoder.encode(pass));
        u.setEmail(email);
        u.setType(type);
        userRepository.save(u);
    }

    public void saveChanges(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String Name) {
        userRepository.deleteByName(Name);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> readAll() {
        log.info("Find all users");
        List <User> users = userRepository.findAll();
        users.sort(Comparator.comparingInt(User::getId));
        return users;
    }
}
