package security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.security.domain.Cocomment;
import security.security.domain.Comment;

import java.util.List;

@Repository
public interface CocommentRepository extends JpaRepository<Cocomment, Long> {
    List<Cocomment> findAllByComment(Comment comment);
}
