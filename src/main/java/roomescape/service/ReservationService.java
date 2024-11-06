package roomescape.service;

import roomescape.entity.Reservation;
import roomescape.repository.ReservationRepositoryImpl;

import java.util.List;

public class ReservationService {
    private final ReservationRepositoryImpl reservationRepository;

    public ReservationService(ReservationRepositoryImpl reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> readReservations() {
        return reservationRepository.findAll();
    }
}
