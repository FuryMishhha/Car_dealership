package com.example.demo.Services;

import lombok.RequiredArgsConstructor;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
/**
 * Класс UserService, который отвечает за бизнес-логику работы с пользователями.
 * Имеет два приватных свойства - userRepository и encoder.
 * Имплементирует интерфейс UserDetailService
 */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    /** Репозиторий, необходимый для работы с базой пользователей*/
    @Autowired
    private UserRepository userRepository;

    /**
     * Поле encoder обозначает, каким алгоритмом будут шифроваться данные пользователей
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Метод поиска пользователя по имени
     * @param s имя пользователя
     * @return объект класса UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findByName(s);
        return u;
    }

    /**
     * Метод создания пользователя
     * @param name имя пользователя
     * @param pass пароль пользователя
     * @param email почта пользователя
     * @param type роль пользователя
     */
    public void create(String name, String pass, String email, String type) {
        User u = new User();
        u.setName(name);
        u.setPassword(bCryptPasswordEncoder.encode(pass));
        u.setEmail(email);
        u.setType(type);
        userRepository.save(u);
    }

    /**
     * Метод сохранения изменений
     * @param user - пользователь
     */
    public void saveChanges(User user) {
        userRepository.save(user);
    }

    /**
     * Метод удаления пользователя
     * @param Name - имя пользователя
     */
    public void deleteUser(String Name) {
        userRepository.deleteByName(Name);
    }

    /**
     * Метод нахождения пользователя
     * @param name имя пользователя
     * @return объект класса User
     */
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Метод вывода списка всех пользователей
     * @return объект класса User
     */
    public List<User> readAll() {
        log.info("Find all users");
        List <User> users = userRepository.findAll();
        users.sort(Comparator.comparingInt(User::getId));
        return users;
    }
}
