package security.security.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Cocomment;
import security.security.domain.Comment;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentViewDTO {

    private Long commentId;


    private String content;

    List<Cocomment> cocommentList = new ArrayList<>();
    private String name;


    public CommentViewDTO(final Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.name = comment.getMember().getName();
    }
}
