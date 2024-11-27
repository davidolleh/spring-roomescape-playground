package roomescape.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.entity.Person;
import roomescape.entity.Reservation;
import roomescape.entity.Time;
import roomescape.exception.EntityNotFoundException;
import roomescape.util.CustomDateTimeFormat;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class ReservationDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reservation> reservationRowMapper = (rs, rowNum) -> new Reservation(
            rs.getLong("reservation_id"),
            new Person(rs.getString("name")),
            LocalDate.parse(rs.getString("date"), CustomDateTimeFormat.dateFormatter),
            new Time(rs.getLong("time_id'"), LocalTime.parse(rs.getString("time"), CustomDateTimeFormat.timeFormatter))
    );

    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reservation save(Reservation reservation) {
        String query = "INSERT INTO RESERVATION (name, date, time_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    query,
                    new String[]{"id"});
            ps.setString(1, reservation.getPerson().getName());
            ps.setString(2, reservation.getDate().format(CustomDateTimeFormat.dateFormatter));
            ps.setLong(3, reservation.getTimeId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new Reservation(
                id,
                reservation.getPerson(),
                reservation.getDate(),
                reservation.getTime()
        );
    }

    public List<Reservation> findAll() {
        String query = "SELECT \n" +
                "    r.id as reservation_id, \n" +
                "    r.name, \n" +
                "    r.date, \n" +
                "    t.id as time_id, \n" +
                "    t.time as time_value \n" +
                "FROM reservation as r inner join time as t on r.time_id = t.id";

        return jdbcTemplate.query(query, reservationRowMapper);
    }

    public Reservation findById(Long id) {
        String query = "SELECT * FROM RESERVATION WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(query, reservationRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteById(Long id) {
        String query = "delete from RESERVATION where id = ?";
        int count = jdbcTemplate.update(query, id);

        if (count == 0) {
            throw new EntityNotFoundException("해당 예약은 존재하지 않스빈다");
        }
    }
}
