package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Reservation;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRpository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Chambre c " +
            "JOIN c.reservations r " +
            "JOIN c.bloc b " +
            "JOIN b.foyers f " +
            "JOIN f.universite u " +
            "WHERE r.anneeUniversitaire = :anneeUniversite " +
            "AND u.nomUniversite = :nomUniversite")
    public List<Reservation> findByAnneeUniversitaireEtNomUniversite(
            @Param("anneeUniversite") Date anneeUniversitaire,
            @Param("nomUniversite") String nomUniversite);
}
