package security.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Friend;
import security.security.domain.MemberEntity;
import security.security.domain.Schedule;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    private Long userId;
    private String name;
    private List<Friend> friends = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();

    public MemberDTO(final MemberEntity member) {
        this.name = member.getName();
        this.friends = member.getFriends();
        this.schedules = member.getSchedules();
    }

    public static MemberEntity toEntity(final MemberDTO dto) {
        return MemberEntity.builder()
                .name(dto.getName())
                .build();
    }
}
