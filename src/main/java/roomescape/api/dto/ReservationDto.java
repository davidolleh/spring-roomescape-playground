package roomescape.api.dto;

import roomescape.entity.Person;
import roomescape.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record ReservationDto(
        Long id,
        String name,
        String date,
        String time
) {
    private static final String dateFormat = "yyyy-MM-dd";
    private static final String timeFormat = "HH:mm";

    public static ReservationDto fromEntity(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getPerson().getName(),
                reservation.getDate().format(DateTimeFormatter.ofPattern(dateFormat)),
                reservation.getTime().format(DateTimeFormatter.ofPattern(timeFormat))
        );
    }

    public Reservation toEntity() {
        return new Reservation(
                new Person(this.name),
                LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat)),
                LocalTime.parse(time, DateTimeFormatter.ofPattern(timeFormat))
        );
    }
}
