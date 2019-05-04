package me.tiagofernandes.todolistapi.business.services;

import me.tiagofernandes.todolistapi.business.repositories.IUserRepository;
import me.tiagofernandes.todolistapi.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private IUserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    UserService(IUserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);

    }

    public String saveUser(User user) {
        User savedUser = userRepository.save(user);
        return tokenService.createToken(savedUser.getId());
    }
}
