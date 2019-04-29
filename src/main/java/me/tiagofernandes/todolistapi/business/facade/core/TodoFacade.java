package me.tiagofernandes.todolistapi.business.facade.core;

import me.tiagofernandes.todolistapi.business.facade.ITodoListFacade;
import me.tiagofernandes.todolistapi.business.repositories.TodoListRepository;
import me.tiagofernandes.todolistapi.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoFacade implements ITodoListFacade {
    @Autowired
    TodoListRepository todoListRepository;

    @Override
    public List<Todo> findAll() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return todoListRepository.findAll(sortByCreatedAtDesc);
    }

    @Override
    public Optional<Todo> findById(String id) {
        return todoListRepository.findById(id);
    }


    @Override
    public Todo addTodo(Todo todo) {
        return todoListRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return todoListRepository.save(todo);
//                .map(todoData -> {
//                    todoData.setTask(todo.getTask());
//                    todoData.setCompleted((todo.getCompleted()));
//                    return todoListRepository.save(todoData);
//                });
    }

    @Override
    public Optional<Todo> removeTodo(String id) {
        return todoListRepository.findById(id)
                .map(todo -> {
                    todoListRepository.deleteById(id);
                    return null;
                });
    }

    @Override
    public Todo setAsCompleted(String id, Boolean completed) {
        return null;
    }

    @Override
    public Todo setAsIncomplete(String id, Boolean completed) {
        return null;
    }

}
