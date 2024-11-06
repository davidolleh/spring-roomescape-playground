package roomescape.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<ReservationDto>> readReservations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        reservationService.readReservations().stream().
                                map(ReservationDto::fromEntity)
                                .toList()
                );
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ReservationDto.fromEntity(
                                reservationService.createReservation(reservationDto.toEntity())
                        )
                );
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteReservation(@PathVariable List<Long> id) {
        reservationService.deleteReservation(id.get(0));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
