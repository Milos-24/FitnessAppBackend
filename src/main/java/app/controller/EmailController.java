package app.controller;

import app.entities.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EmailController {

    private final UserService userService;
    @GetMapping(path = "/register/confirm/{id}")
    public Boolean confirm(@PathVariable("id") int id) {
        User user = userService.confirmId(id);

        return user != null;
    }
}
