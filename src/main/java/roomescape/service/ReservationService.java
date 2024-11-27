package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import roomescape.entity.Reservation;
import roomescape.entity.Time;
import roomescape.repository.ReservationDao;
import roomescape.repository.TimeDao;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDao reservationRepository;
    private final TimeDao reservationTimeDao;

    public ReservationService(
            @Autowired ReservationDao reservationRepository,
            @Autowired TimeDao reservationTimeDao
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationTimeDao = reservationTimeDao;
    }

    public List<Reservation> readReservations() {
        return reservationRepository.findAll();
    }

    public Reservation readReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Time> readReservationTimes() {
        return reservationTimeDao.findAll();
    }

    public Time createReservationTime(roomescape.entity.Time reservationTime) {
        return reservationTimeDao.save(reservationTime);
    }

    public void deleteReservationTime(Long id) {
        reservationTimeDao.delete(id);
    }
}
