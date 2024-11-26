package roomescape.entity;

import java.time.LocalTime;

public class ReservationTime {
    private Long id;
    private LocalTime time;

    public ReservationTime(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
}
