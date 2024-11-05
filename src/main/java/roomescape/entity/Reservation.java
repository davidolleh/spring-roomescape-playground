package roomescape.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation implements  Comparable<Long> {
    private Long id;
    private Person person;
    private LocalDate date;
    private LocalTime time;

    public Reservation(Person person, LocalDate date, LocalTime time) {
        this.id = 0L;
        this.person = person;
        this.date = date;
        this.time = time;
    }

    @Override
    public int compareTo(Long o) {
        return this.id.compareTo(o);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
