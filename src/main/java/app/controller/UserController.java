package app.controller;


import app.entities.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/usernames/{username}")
    public ResponseEntity<List<String>> getUsernames(@PathVariable("username") String username)
    {
        List<String> usernames = userService.getAllUsernames(username);
        return new ResponseEntity<>(usernames, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/update")
    public ResponseEntity<?> editUser(@RequestBody User user)
    {
        return userService.edit(user);
    }
}
