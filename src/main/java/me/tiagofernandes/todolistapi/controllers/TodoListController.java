package me.tiagofernandes.todolistapi.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.tiagofernandes.todolistapi.models.Todo;
import me.tiagofernandes.todolistapi.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TodoListController {

    @Autowired
    TodoListRepository todoListRepository;

    @JsonProperty("todos")
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return todoListRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        return todoListRepository.save(todo);
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id) {
        return todoListRepository.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody Todo todo) {
        return todoListRepository.findById(id)
                .map(todoData -> {
                    todoData.setTask(todo.getTask());
                    todoData.setCompleted(todo.getCompleted());
                    Todo updatedtodo = todoListRepository.save(todoData);
                    return ResponseEntity.ok().body(updatedtodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return todoListRepository.findById(id)
                .map(todo -> {
                    todoListRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
