package me.tiagofernandes.todolistapi.controllers;

import me.tiagofernandes.todolistapi.business.facade.ITodoListFacade;
import me.tiagofernandes.todolistapi.controllers.dto.DtoCompleted;
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
        return createdOrNoContent(todoListFacade.addTodo(todo));
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("id") String id) {
        return okOrNotFound(todoListFacade.findById(id));

    }

    @PutMapping(value = "/todos")
    public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo) {
        return okOrNotFound(todoListFacade.updateTodo(todo));
    }

    @PutMapping(value = "/todos/setAsCompleted")
    public ResponseEntity<Optional<Todo>> setAsCompleted(@RequestBody DtoCompleted completed) {
        return okOrNotFound(todoListFacade.setAsCompleted(completed.getId(), completed.getCompleted()));
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<Optional<?>> deleteTodo(@PathVariable("id") String id) {
        return okOrNotFound(todoListFacade.removeTodo(id));
    }

}
