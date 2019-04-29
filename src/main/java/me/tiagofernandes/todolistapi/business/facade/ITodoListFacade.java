package me.tiagofernandes.todolistapi.business.facade;

import me.tiagofernandes.todolistapi.models.Todo;

import java.util.List;
import java.util.Optional;

public interface ITodoListFacade {
    List<Todo> findAll();

    Optional<Todo> findById(String id);

    Todo addTodo(Todo todo);

    Todo updateTodo(Todo todo);

    Optional<Todo> removeTodo(String id);

    Todo setAsCompleted(String id, Boolean completed);

    Todo setAsIncomplete(String id, Boolean completed);
}
