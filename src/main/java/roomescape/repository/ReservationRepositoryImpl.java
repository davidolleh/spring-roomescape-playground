package roomescape.repository;

import roomescape.entity.Person;
import roomescape.entity.Reservation;
import roomescape.exception.EntityNotFoundException;
import roomescape.service.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ReservationRepositoryImpl implements ReservationRepository {
    private final Map<Long, Reservation> reservations;
    private final AtomicLong id = new AtomicLong(1);

    public ReservationRepositoryImpl() {
        reservations = initialReservationsSetting();
    }

    private Map<Long, Reservation> initialReservationsSetting() {
        Person person = new Person("brown");
        Reservation reservation1 = new Reservation(
                id.getAndIncrement(),
                person,
                LocalDate.of(2023,1,1),
                LocalTime.of(10, 0,0)
        );
        Reservation reservation2 = new Reservation(
                id.getAndIncrement(),
                person,
                LocalDate.of(2023,1,2),
                LocalTime.of(11, 0,0)
        );
        Reservation reservation3 = new Reservation(
                id.getAndIncrement(),
                person,
                LocalDate.of(2023,1,3),
                LocalTime.of(12, 0,0)
        );


        return new HashMap<>() {{
            put(reservation1.getId(), reservation1);
            put(reservation2.getId(), reservation2);
            put(reservation3.getId(), reservation3);
        }};
    }

    @Override
    public Reservation save(Reservation reservation) {
        Long newId = id.getAndIncrement();

        reservation.setId(newId);
        reservations.put(newId, reservation);

        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return reservations.values()
                .stream()
                .sorted()
                .toList();
    }

    @Override
    public Reservation findById(Long id) {
        return reservations.get(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservations.containsKey(id)) {
            throw new EntityNotFoundException("예약이 존재하지 않습니다");
        }
        reservations.remove(id);
    }
}
