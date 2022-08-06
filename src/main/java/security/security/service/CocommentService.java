package security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.Cocomment;
import security.security.domain.Comment;
import security.security.domain.Posting;
import security.security.repository.CocommentRepository;
import security.security.repository.CommentRepository;

import java.util.List;


@Service
public class CocommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CocommentRepository cocommentRepository;

    public Cocomment create(Cocomment cocomment) {
        return cocommentRepository.save(cocomment);
    }

    public List<Cocomment> findCocomments(Comment comment) {
        return cocommentRepository.findAllByComment(comment);
    }



}
