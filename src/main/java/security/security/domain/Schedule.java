package security.security.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {


    @Id
    @GeneratedValue
    @Column(name="schedule_id")
    private Long scheduleId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity member;

    private String userName;
    private String date;

    @JsonManagedReference
    @OneToMany(mappedBy = "schedule")
    private List<Todo> schedules = new ArrayList<>();
}
