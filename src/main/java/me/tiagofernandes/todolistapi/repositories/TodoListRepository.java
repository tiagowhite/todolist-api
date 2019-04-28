package me.tiagofernandes.todolistapi.repositories;

import me.tiagofernandes.todolistapi.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends MongoRepository<Todo, String> {
}
