package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roomescape.entity.Reservation;
import roomescape.entity.Time;
import roomescape.repository.ReservationDao;
import roomescape.repository.TimeDao;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;
    private final TimeDao timeDao;

    public ReservationService(
            @Autowired ReservationDao reservationRepository,
            @Autowired TimeDao timeDao
    ) {
        this.reservationDao = reservationRepository;
        this.timeDao = timeDao;
    }

    public List<Reservation> readReservations() {
        return reservationDao.findAll();
    }

    public Reservation readReservation(Long id) {
        return reservationDao.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationDao.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationDao.deleteById(id);
    }
}
