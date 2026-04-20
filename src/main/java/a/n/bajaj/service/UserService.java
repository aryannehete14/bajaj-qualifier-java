package a.n.bajaj.service;

import a.n.bajaj.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    // GET all users
    public List<User> getAllUsers() {
        return users;
    }

    // ADD user
    public User addUser(User user) {
        users.add(user);
        return user;
    }

    // GET user by ID
    public User getUserById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // DELETE user
    public String deleteUser(int id) {
        users.removeIf(u -> u.getId() == id);
        return "User deleted";
    }
}