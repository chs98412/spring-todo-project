package security.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.security.domain.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByTodoId(Long id);

}
