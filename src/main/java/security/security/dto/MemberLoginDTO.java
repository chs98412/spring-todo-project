package security.security.dto;


import lombok.Getter;
import lombok.Setter;
import security.security.domain.MemberEntity;

@Getter
@Setter
public class MemberLoginDTO {

    private String name;
    private String username;
    private String password;

    public static MemberEntity toEntity(final MemberLoginDTO dto) {
        return MemberEntity.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .name(dto.getName())
                .build();
    }
}