package roomescape.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.api.dto.ReservationRequestDto;
import roomescape.api.dto.ReservationResponseDto;
import roomescape.api.dto.TimeRequestDto;
import roomescape.api.dto.TimeResponseDto;
import roomescape.entity.Reservation;
import roomescape.entity.Time;
import roomescape.service.ReservationService;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(
            @Autowired ReservationService reservationService
    ) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDto>> readReservations() {
        return ResponseEntity
                .ok()
                .body(
                        reservationService.readReservations().stream().
                                map(ReservationResponseDto::fromEntity)
                                .toList()
                );
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponseDto> readReservations(@PathVariable Long id) {
        Reservation reservation = reservationService.readReservation(id);

        return ResponseEntity
                .ok()
                .body(ReservationResponseDto.fromEntity(reservation));
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> createReservation(
            @RequestBody @Valid ReservationRequestDto reservationDto
    ) {
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

    @GetMapping("/times")
    public ResponseEntity<List<TimeResponseDto>> readTimes() {
        List<Time> times = reservationService.readReservationTimes();
        List<TimeResponseDto> timeResponseDtos = times.stream()
                .map(TimeResponseDto::fromEntity)
                .toList();

        return ResponseEntity
                .ok()
                .body(timeResponseDtos);
    }

    @PostMapping("/times")
    public ResponseEntity<TimeResponseDto> createTime(@RequestBody TimeRequestDto timeRequestDto) {
        Time time = reservationService.createReservationTime(TimeRequestDto.toEntity(timeRequestDto));
        TimeResponseDto timeResponseDto = TimeResponseDto.fromEntity(time);

        String headerName = "Location";
        String headerValue = "/times/" + timeResponseDto.id();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(headerName, headerValue)
                .body(timeResponseDto);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        reservationService.deleteReservationTime(id);

        return ResponseEntity.noContent().build();
    }
}
