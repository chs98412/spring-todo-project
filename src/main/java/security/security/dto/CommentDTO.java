package security.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Comment;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {

    private String content;
    private MemberEntity member;
    private Posting posting;

    public CommentDTO(final Comment comment) {
        this.content = comment.getContent();
        this.member = comment.getMember();
        this.posting=comment.getPosting();
    }

    public static Comment toEntity(final CommentDTO dto) {
        return Comment.builder()
                .member(dto.getMember())
                .content(dto.getContent())
                .posting(dto.getPosting())
                .build();
    }


}