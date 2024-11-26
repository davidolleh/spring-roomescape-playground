package roomescape.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.entity.Person;
import roomescape.entity.Reservation;
import roomescape.exception.EntityNotFoundException;
import roomescape.service.ReservationRepository;
import roomescape.util.CustomDateTimeFormat;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class JDBCReservationDao implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reservation> reservationRowMapper = (rs, rowNum) -> new Reservation(
            rs.getLong("id"),
            new Person(rs.getString("name")),
            LocalDate.parse(rs.getString("date"), CustomDateTimeFormat.dateFormatter),
            LocalTime.parse(rs.getString("time"), CustomDateTimeFormat.timeFormatter)
    );

    public JDBCReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reservation save(Reservation reservation) {
        String query = "INSERT INTO RESERVATION (name, date, time) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    query,
                    new String[]{"id"});
            ps.setString(1, reservation.getPerson().getName());
            ps.setString(2, reservation.getDate().format(CustomDateTimeFormat.dateFormatter));
            ps.setString(3, reservation.getTime().format(CustomDateTimeFormat.timeFormatter));
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

    @Override
    public List<Reservation> findAll() {
        String query = "SELECT * FROM RESERVATION";

        return jdbcTemplate.query(query, reservationRowMapper);
    }

    @Override
    public Reservation findById(Long id) {
        String query = "SELECT * FROM RESERVATION WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(query, reservationRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = "delete from RESERVATION where id = ?";
        int count = jdbcTemplate.update(query, id);

        if (count == 0) {
            throw new EntityNotFoundException("해당 예약은 존재하지 않스빈다");
        }
    }
}
