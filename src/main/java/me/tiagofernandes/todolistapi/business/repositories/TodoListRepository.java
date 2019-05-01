package me.tiagofernandes.todolistapi.business.repositories;

import me.tiagofernandes.todolistapi.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(path = "todos", collectionResourceRel = "todos", itemResourceRel = "todo")
public interface TodoListRepository extends MongoRepository<Todo, String> {
    Optional<Todo> findById(String id);
}
