package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roomescape.entity.Reservation;
import roomescape.entity.Time;
import roomescape.exception.EntityAlreadyExistException;
import roomescape.repository.ReservationDao;
import roomescape.repository.TimeDao;

import java.util.List;
import java.util.Optional;

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
        Optional<Reservation> existReservation = reservationDao.findByDateAndTime(reservation.getDate(), reservation.getTimeId());
        if (existReservation.isPresent()) {
            throw new EntityAlreadyExistException("해당 날짜와 시간의 예약이 이미 존재합니다");
        }

        Time time = timeDao.findById(reservation.getTimeId());

        Reservation reservationWithTimeInfo = new Reservation(
                reservation.getPerson(),
                reservation.getDate(),
                time
        );
        return reservationDao.save(reservationWithTimeInfo, reservation.getTimeId());
    }

    public void deleteReservation(Long id) {
        reservationDao.deleteById(id);
    }
}
