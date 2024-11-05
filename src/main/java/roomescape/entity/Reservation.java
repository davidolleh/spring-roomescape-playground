package roomescape.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private int id;
    private Person person;
    private LocalDate date;
    private LocalTime time;

    public Reservation(int id, Person person, LocalDate date, LocalTime time) {
        this.id = id;
        this.person = person;
        this.date = date;
        this.time = time;
    }
}
