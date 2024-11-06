package roomescape.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.api.dto.ReservationDto;
import roomescape.repository.ReservationRepositoryImpl;
import roomescape.service.ReservationService;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService(new ReservationRepositoryImpl());
    }

    @GetMapping("/reservation")
    public String reservationPage() {
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public List<ReservationDto> readReservations() {
        return reservationService.readReservations().stream().
                map(ReservationDto::fromEntity)
                .toList();
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ReservationDto createReservation(@RequestBody ReservationDto reservationDto) {
        return ReservationDto.fromEntity(
                reservationService.createReservation(reservationDto.toEntity())
        );
    }
}
