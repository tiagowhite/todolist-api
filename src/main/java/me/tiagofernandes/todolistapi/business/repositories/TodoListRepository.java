package me.tiagofernandes.todolistapi.business.repositories;

import me.tiagofernandes.todolistapi.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoListRepository extends MongoRepository<Todo, String> {
    public Optional<Todo> findById(String id);
}
