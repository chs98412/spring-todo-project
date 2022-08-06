package security.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Friend;
import security.security.domain.MemberEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FriendDTO {
    private Long friendId;
    private MemberEntity member;
    private Long frId ;
    private String friendName;


    public FriendDTO(final Friend friend) {
        this.friendId = friend.getFriendId();
        this.member = friend.getMember();
        this.frId = friend.getFrId();
        this.friendName=friend.getFriendName();
    }

    public static Friend toEntity(final FriendDTO dto) {
        return Friend.builder()
                .friendId(dto.getFriendId())
                .member(dto.getMember())
                .frId(dto.getFrId())
                .friendName(dto.getFriendName())
                .build();
    }
}