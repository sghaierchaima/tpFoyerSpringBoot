package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRpository;

import java.util.List;
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements  IReservationService{
    @Autowired
    private ReservationRpository reservationRpository;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRpository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRpository.save(res);
    }

    @Override
    public Reservation retrieveReservation(long idReservation) {
        return reservationRpository.findById(idReservation).isPresent()?reservationRpository.findById(idReservation).get():null;
    }
}
