package roomescape.api.dto;

import roomescape.entity.Person;
import roomescape.entity.Reservation;
import roomescape.util.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public record ReservationRequestDto(
        String name,
        String date,
        String time
) {

    public ReservationRequestDto {
        checkValidation(name, date, time);
    }

    private void checkValidation(String name, String date, String time) {
        blanksValidation(name, date, time);
    }

    private void blanksValidation(String... fields) {
        boolean isBlankExists = Arrays.stream(fields)
                .anyMatch(String::isBlank);

        if (isBlankExists) {
            throw new IllegalArgumentException("요청 인자의 값을 빈값일 수 없습니다");
        }
    }


    public Reservation toEntity() {
        return new Reservation(
            new Person(this.name),
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DateTimeFormat.dateFormat)),
            LocalTime.parse(time, DateTimeFormatter.ofPattern(DateTimeFormat.timeFormat))
        );
    }
}
