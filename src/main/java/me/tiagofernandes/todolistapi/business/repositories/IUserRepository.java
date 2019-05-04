package me.tiagofernandes.todolistapi.business.repositories;

import me.tiagofernandes.todolistapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);
}
