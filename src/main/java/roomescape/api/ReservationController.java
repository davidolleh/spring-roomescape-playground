package roomescape.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.api.dto.ReservationRequestDto;
import roomescape.api.dto.ReservationResponseDto;
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
//        throw new IllegalArgumentException();
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<ReservationResponseDto>> readReservations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        reservationService.readReservations().stream().
                                map(ReservationResponseDto::fromEntity)
                                .toList()
                );
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto reservationDto) {
        ReservationResponseDto response =
                ReservationResponseDto.fromEntity(reservationService.createReservation(reservationDto.toEntity()));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/reservations/"+ response.id())
                .body(response);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
