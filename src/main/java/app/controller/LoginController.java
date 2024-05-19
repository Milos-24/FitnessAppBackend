package app.controller;

import app.entities.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user)
    {
        return userService.register(user);
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userService.login(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        } else if (!user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        } else if (!user.getEnabled()) {
            userService.sendConfirmationMail(username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is not active, please activate via email!");
        }

        return ResponseEntity.ok().build();
    }
}