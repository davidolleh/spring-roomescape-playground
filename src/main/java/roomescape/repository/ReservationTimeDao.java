package roomescape.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.entity.ReservationTime;
import roomescape.util.CustomDateTimeFormat;

import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.List;

@Repository
public class ReservationTimeDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<ReservationTime> timeRowMapper = (rs, rowNum) -> new ReservationTime(
            rs.getLong("id"),
            LocalTime.parse(rs.getString("time"), CustomDateTimeFormat.timeFormatter)
    );

    public ReservationTimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReservationTime> findAll() {
        String query = "SELECT * FROM time";

        return jdbcTemplate.query(query, timeRowMapper);
    }

    public ReservationTime save(ReservationTime reservationTime) {
        String query = "INSERT INTO time (time) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    query,
                    new String[]{"id"});
            ps.setString(1, reservationTime.getTime().format(CustomDateTimeFormat.timeFormatter));
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new ReservationTime(
                id,
                reservationTime.getTime()
        );
    }
}
