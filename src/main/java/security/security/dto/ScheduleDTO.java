package security.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.MemberEntity;
import security.security.domain.Schedule;
import security.security.domain.Todo;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDTO {
    private String userName;
    private Long scheduleId;
    private MemberEntity member;
    private List<Todo> schedules = new ArrayList<>();

    private String date;



    public ScheduleDTO(final Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.member = schedule.getMember();
        this.schedules = schedule.getSchedules();
        this.date=schedule.getDate();
        this.userName = schedule.getUserName();

    }

    public static Schedule toEntity(final ScheduleDTO dto) {
        return Schedule.builder()
                .scheduleId(dto.getScheduleId())
                .member(dto.getMember())
                .schedules(dto.getSchedules())
                .date(dto.getDate())
                .userName(dto.getUserName())
                .build();
    }

}
