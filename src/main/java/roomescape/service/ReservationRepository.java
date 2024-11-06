package roomescape.service;

import roomescape.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    Reservation findById(Long id);
    void deleteById(Long id);
}
