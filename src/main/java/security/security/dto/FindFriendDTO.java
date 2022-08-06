package security.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Friend;
import security.security.domain.MemberEntity;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindFriendDTO {
    private String userName;
    private String friendName;

    private Long friendId;
    private MemberEntity member;
    private Long frId ;

    public FindFriendDTO(final Friend friend) {
        this.friendId = friend.getFriendId();
        this.member = friend.getMember();
        this.friendName=friend.getFriendName();
        this.frId = friend.getFrId();
    }

    public static Friend toEntity(final FindFriendDTO dto) {
        return Friend.builder()
                .friendId(dto.getFriendId())
                .member(dto.getMember())
                .frId(dto.getFrId())
                .friendName(dto.getFriendName())
                .build();
    }
}