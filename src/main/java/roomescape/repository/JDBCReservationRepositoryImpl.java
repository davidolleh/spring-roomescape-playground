package roomescape.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import roomescape.entity.Person;
import roomescape.entity.Reservation;
import roomescape.service.ReservationRepository;
import roomescape.util.CustomDateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class JDBCReservationRepositoryImpl implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCReservationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        String query = "SELECT * FROM RESERVATION";

        return jdbcTemplate.query(query, (rs, rowNum) -> new Reservation(
                rs.getLong("id"),
                new Person(rs.getString("name")),
                LocalDate.parse(rs.getString("date"), CustomDateTimeFormat.dateFormatter),
                LocalTime.parse(rs.getString("time"), CustomDateTimeFormat.timeFormatter)
        ));
    }

    @Override
    public Reservation findById(Long id) {
        String query = "SELECT * FROM RESERVATION WHERE id = ?";

        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> new Reservation(
                rs.getLong("id"),
                new Person(rs.getString("name")),
                LocalDate.parse(rs.getString("date"), CustomDateTimeFormat.dateFormatter),
                LocalTime.parse(rs.getString("time"), CustomDateTimeFormat.timeFormatter)
        ), id);
    }

    @Override
    public void deleteById(Long id) {

    }
}
