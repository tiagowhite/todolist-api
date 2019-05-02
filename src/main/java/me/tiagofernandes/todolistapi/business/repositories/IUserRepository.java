package me.tiagofernandes.todolistapi.business.repositories;

import me.tiagofernandes.todolistapi.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<Users, String> {
    Users findByUsername(String username);
}
