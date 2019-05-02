package me.tiagofernandes.todolistapi.business.facade.core;

import me.tiagofernandes.todolistapi.business.exception.BusinessException;
import me.tiagofernandes.todolistapi.business.facade.ITodoListFacade;
import me.tiagofernandes.todolistapi.business.repositories.ITodoListRepository;
import me.tiagofernandes.todolistapi.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class TodoFacade implements ITodoListFacade {
    @Autowired
    ITodoListRepository ITodoListRepository;

    @Override
    public List<Todo> findAll() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return ITodoListRepository.findAll(sortByCreatedAtDesc);
    }

    @Override
    public Optional<Todo> findById(String id) {
        return ITodoListRepository.findById(id);
    }


    @Override
    public Todo addTodo(Todo todo) {
        return ITodoListRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        if (isBlank(todo.getTask())) {
            throw new BusinessException("Titulo nao encontrado");
        }
        return ITodoListRepository.save(todo);
    }

    @Override
    public Optional<Todo> removeTodo(String id) {
        return ITodoListRepository.findById(id)
                .map(todo -> {
                    ITodoListRepository.delete(todo);
                    return todo;
                });
    }

    public Optional<Todo> setAsCompleted(String id, Boolean completed) {
        return ITodoListRepository.findById(id)
                .map(todo -> {
                    todo.setCompleted(completed);
                    return ITodoListRepository.save(todo);
                });
    }


}
