package me.tiagofernandes.todolistapi.controllers;

import me.tiagofernandes.todolistapi.business.services.UserService;
import me.tiagofernandes.todolistapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String regiterUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get")
    public Optional<User> getUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.getUser(userId);
    }
}
