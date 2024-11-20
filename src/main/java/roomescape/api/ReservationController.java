package roomescape.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.api.dto.ReservationRequestDto;
import roomescape.api.dto.ReservationResponseDto;
import roomescape.entity.Reservation;
import roomescape.service.ReservationRepository;
import roomescape.service.ReservationService;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService(new ReservationRepository());
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDto>> readReservations() {
        List<Reservation> reservations = reservationService.readReservations();

        List<ReservationResponseDto> response
                = reservations.stream()
                .map(ReservationResponseDto::fromEntity)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto reservationDto) {
        Reservation reservation = reservationService.createReservation(reservationDto.toEntity());

        ReservationResponseDto response =
                ReservationResponseDto.fromEntity(reservation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/reservations/"+ response.id())
                .body(response);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
