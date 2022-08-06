package security.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.Comment;
import security.security.domain.Posting;
import security.security.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findComments(Posting posting) {
        return commentRepository.findAllByPosting(posting);
    }

    public Comment find(Long id) {
        return commentRepository.findById(id).get();
    }
}
