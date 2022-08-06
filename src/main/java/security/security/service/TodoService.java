package security.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.Todo;
import security.security.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public List<Todo> create(Todo todo) {
        repository.save(todo);

        return repository.findByTodoId(todo.getTodoId());

    }
    public Todo createTodo(Todo todo) {
        return repository.save(todo);



    }
}
