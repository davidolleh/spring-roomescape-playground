package roomescape.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import roomescape.entity.Person;
import roomescape.entity.Reservation;
import roomescape.util.CustomDateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public record ReservationRequestDto(
        @NotBlank
        @NotNull
        String name,
        @NotBlank
        @NotNull
        String date,
        @NotBlank
        @NotNull
        String time
) {
    public Reservation toEntity() {
        return new Reservation(
            new Person(this.name),
            LocalDate.parse(date, CustomDateTimeFormat.dateFormatter),
            LocalTime.parse(time, CustomDateTimeFormat.timeFormatter)
        );
    }
}
