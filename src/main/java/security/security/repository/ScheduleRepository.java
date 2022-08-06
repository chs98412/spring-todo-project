package security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.security.domain.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByScheduleId(Long id);
    Schedule findByScheduleId(Long id);

    Schedule findByUserNameAndDate(String username, String Date);
}
