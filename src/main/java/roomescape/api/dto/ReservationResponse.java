package roomescape.api.dto;

import roomescape.entity.Reservation;

import java.time.format.DateTimeFormatter;

public record ReservationResponse(
        Long id,
        String name,
        String date,
        String time
) {
    private static final String dateFormat = "yyyy-MM-dd";
    private static final String timeFormat = "HH:mm";

    public static ReservationResponse fromEntity(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getPerson().getName(),
                reservation.getDate().format(DateTimeFormatter.ofPattern(dateFormat)),
                reservation.getTime().format(DateTimeFormatter.ofPattern(timeFormat))
        );
    }
}
