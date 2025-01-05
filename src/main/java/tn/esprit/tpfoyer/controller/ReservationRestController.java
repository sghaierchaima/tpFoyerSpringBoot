package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {
    @Autowired
    private IReservationService reservationService;
    @Operation(description = "Récuperer tous les reservations")
    @GetMapping("/retrives-all-reservation")
    public List<Reservation> getReservations(){
        List<Reservation> listReservation = reservationService.retrieveAllReservation();
        return listReservation;
    }


    @Operation(description="Récupérer une réservation par son ID")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id")long reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    //http://localhost:8089/foyer/reservation/modify-reservation
    @Operation(description="Modifier une réservation")
    @PutMapping("/update-reservation")
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation);
    }
}
