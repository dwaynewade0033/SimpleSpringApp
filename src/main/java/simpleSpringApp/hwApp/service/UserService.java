package simpleSpringApp.hwApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simpleSpringApp.hwApp.model.User;
import simpleSpringApp.hwApp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public void updateUserById(User user, int id) {
        userRepository.updateUserById(user, id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    public User getUserById(int id) {
        return userRepository.findByUserId(id);
    }
}
