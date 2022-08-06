package security.security.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import security.security.domain.MemberEntity;

@Getter
@Setter
public class MemberFormDTO {

    //@NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    //@NotEmpty(message = "이메일은 필수 입력 값입니다.")
    //@Email(message = "이메일 형식으로 입력해주세요.")
    private String name;

    //@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    //@Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    //@NotEmpty(message = "주소는 필수 입력 값입니다.")
    //private String address;

    public static MemberEntity toEntity(final MemberFormDTO dto, PasswordEncoder passwordEncoder) {
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        return MemberEntity.builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .password(passwordEncoder2.encode(dto.getPassword()))
                .build();
    }

}