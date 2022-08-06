package security.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Cocomment;
import security.security.domain.Comment;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CocommentDTO {
    private Long Commentid;
    private String content;
    private MemberEntity member;
    private Comment comment;




    public static Cocomment toEntity(final CocommentDTO dto) {
        return Cocomment.builder()
                .member(dto.getMember())
                .content(dto.getContent())
                .comment(dto.getComment())
                .build();
    }
}
