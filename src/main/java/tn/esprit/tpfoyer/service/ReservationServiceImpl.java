package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.*;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.repository.ReservationRpository;

import java.sql.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements  IReservationService{
    @Autowired
    private ReservationRpository reservationRpository;
    BlocRepository blocRepository;
    EtudiantRepository etudiantRepository;
    ChambreRepository chambreRepository;
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

    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        Bloc b = blocRepository.findById(idBloc).orElse(null);
        Etudiant e=etudiantRepository.findByCin(cinEtudiant).orElse(null);
        assert b != null;
        Chambre c = b.getChambres().stream()
                .filter(ch -> ch.getReservations().size() < getCapaciteMaximale(ch.getTupeC()))
                .findFirst().orElse(null);

        if (c==null) {
            throw new RuntimeException("Aucune chambre disponible pour ce bloc");
        }


        Reservation r= new Reservation();
        r.setIdReservation(Long.valueOf(c.getNumeroChambre()+"-"+b.getNomBloc()+"-"+r.getAnneeUniversitaire()));
        r.setEstValide(true);
        r.getEtudiants().add(e);
        c.getReservations().add(r);
        reservationRpository.save(r);
        chambreRepository.save(c);
        return r;
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant e=etudiantRepository.findByCin(cinEtudiant).orElse(null);
        assert e != null;
        Reservation r = e.getReservations().stream()
                .filter(Reservation::getestValide)
                .findFirst()
                .orElse(null);

        assert r != null;
        r.setEstValide(false);

        r.getEtudiants().remove(e);

        Chambre chambreAssociee = chambreRepository.findAll().stream()
                .filter(chambre -> chambre.getReservations().contains(r))
                .findFirst()
                .orElse(null);

        assert chambreAssociee != null;
        chambreAssociee.getReservations().remove(r);

        reservationRpository.save(r);
        chambreRepository.save(chambreAssociee);

        return r;

    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite) {
        return reservationRpository.findByAnneeUniversitaireEtNomUniversite(anneeUniversitaire, nomUniversite);
    }


    public int getCapaciteMaximale(TypeChambre typeChambre) {
        switch (typeChambre) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                throw new RuntimeException("Type de chambre non valide");
        }
    }
}
