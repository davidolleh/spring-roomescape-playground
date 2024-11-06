package roomescape.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.api.dto.ReservationResponse;
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
    public List<ReservationResponse> readReservations() {
        return reservationService.readReservations().stream().
                map(ReservationResponse::fromEntity)
                .toList();
    }
}
