package security.security.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Friend> friends = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Schedule> schedules = new ArrayList<>();

    @Builder
    public MemberEntity(Long id, String username, String password,String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }
}