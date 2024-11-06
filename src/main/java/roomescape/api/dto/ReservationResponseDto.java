package roomescape.api.dto;
import roomescape.entity.Reservation;
import roomescape.util.DateTimeFormat;

import java.time.format.DateTimeFormatter;

public record ReservationResponseDto(
        Long id,
        String name,
        String date,
        String time
) {
    public static ReservationResponseDto fromEntity(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getPerson().getName(),
                reservation.getDate().format(DateTimeFormatter.ofPattern(DateTimeFormat.dateFormat)),
                reservation.getTime().format(DateTimeFormatter.ofPattern(DateTimeFormat.timeFormat))
        );
    }
}
