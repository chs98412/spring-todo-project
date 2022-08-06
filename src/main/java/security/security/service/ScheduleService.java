package security.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.Schedule;
import security.security.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    public List<Schedule> create(final Schedule schedule) {

        repository.save(schedule);
        return repository.findAllByScheduleId(schedule.getScheduleId());

    }
    public Schedule createSchedule(final Schedule schedule) {


        return repository.save(schedule);

    }

    public Schedule find(String username,String date) {
        return repository.findByUserNameAndDate(username,date);
    }


}
