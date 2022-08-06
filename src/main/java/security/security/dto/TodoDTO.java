package security.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.security.domain.Schedule;
import security.security.domain.Todo;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String scheduleDate;
    private String userName;
    private Long todoId;
    private Schedule schedule;
    private String description;



    public TodoDTO(final Todo todo) {
        this.todoId = todo.getTodoId();
        this.schedule = todo.getSchedule();
        this.description = todo.getDescription();
    }

    public static Todo toEntity(final TodoDTO dto) {
        return Todo.builder()
                .todoId(dto.getTodoId())
                .schedule(dto.getSchedule())
                .description(dto.getDescription())
                .build();
    }

}