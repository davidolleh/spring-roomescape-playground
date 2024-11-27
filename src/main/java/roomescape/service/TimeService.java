package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roomescape.entity.Time;
import roomescape.repository.ReservationDao;
import roomescape.repository.TimeDao;

import java.util.List;

@Service
public class TimeService {
    private final ReservationDao reservationDao;
    private final TimeDao timeDao;

    public TimeService(
            @Autowired ReservationDao reservationDao,
            @Autowired TimeDao timeDao
    ) {
        this.reservationDao = reservationDao;
        this.timeDao = timeDao;
    }

    public List<Time> readReservationTimes() {
        return timeDao.findAll();
    }

    public Time createReservationTime(roomescape.entity.Time reservationTime) {
        return timeDao.save(reservationTime);
    }

    public void deleteReservationTime(Long id) {
        timeDao.delete(id);
    }
}
