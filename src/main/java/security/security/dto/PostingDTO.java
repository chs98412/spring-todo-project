package security.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostingDTO {

    private MemberEntity member;
    private String title;
    private String content;


    public PostingDTO(final Posting posting) {
        this.member = posting.getMember();
        this.title = posting.getTitle();
        this.content=posting.getContent();
    }

    public static Posting toEntity(final PostingDTO dto) {
        return Posting.builder()
                .member(dto.getMember())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

}