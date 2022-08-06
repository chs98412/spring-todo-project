package security.security;

import lombok.*;
import security.security.domain.MemberEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .build();
    }

    @Builder
    public MemberDto(Long id, String username, String password,String name) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }
}