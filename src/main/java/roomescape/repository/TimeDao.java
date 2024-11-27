package roomescape.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.entity.Time;
import roomescape.exception.EntityNotFoundException;
import roomescape.util.CustomDateTimeFormat;

import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.List;

@Repository
public class TimeDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<roomescape.entity.Time> timeRowMapper = (rs, rowNum) -> new roomescape.entity.Time(
            rs.getLong("id"),
            LocalTime.parse(rs.getString("time"), CustomDateTimeFormat.timeFormatter)
    );

    public TimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<roomescape.entity.Time> findAll() {
        String query = "SELECT * FROM time";

        return jdbcTemplate.query(query, timeRowMapper);
    }

    public roomescape.entity.Time save(Time reservationTime) {
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

        return new Time(
                id,
                reservationTime.getTime()
        );
    }

    public void delete(Long id) {
        String query = "DELETE FROM time WHERE id = ?";
        int count = jdbcTemplate.update(query, id);

        if (count == 0) {
            throw new EntityNotFoundException("해당 id의 시간은 존재하지 않습니다");
        }
    }
}
