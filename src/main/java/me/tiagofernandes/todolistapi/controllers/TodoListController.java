package me.tiagofernandes.todolistapi.controllers;

import me.tiagofernandes.todolistapi.business.facade.ITodoListFacade;
import me.tiagofernandes.todolistapi.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static me.tiagofernandes.todolistapi.controllers.util.RestResponse.okOrNotFound;
import static me.tiagofernandes.todolistapi.controllers.util.RestResponse.createdOrNoContent;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TodoListController {

    @Autowired
    private ITodoListFacade todoListFacade;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return okOrNotFound(todoListFacade.findAll());
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        return createdOrNoContent(todoListFacade.addTodo(todo));
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("id") String id) {
        return okOrNotFound(todoListFacade.findById(id));

    }

    /*@PutMapping(value = "/todos/{id}")
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
    }*/

}
