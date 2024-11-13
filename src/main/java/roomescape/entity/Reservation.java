package roomescape.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation implements  Comparable<Reservation> {
    private Long id;
    private Person person;
    private LocalDate date;
    private LocalTime time;

    public Reservation(Long id, Person person, LocalDate date, LocalTime time) {
        this.id = id;
        this.person = person;
        this.date = date;
        this.time = time;
    }
    public Reservation(Person person, LocalDate date, LocalTime time) {
        this.id = 0L;
        this.person = person;
        this.date = date;
        this.time = time;
    }

    @Override
    public int compareTo(Reservation o) {
        return (int) (this.id - o.id);
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
