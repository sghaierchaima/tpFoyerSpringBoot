package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    Set<Chambre> findAllByNumeroChambreIn(List<Long> numeroChambre);
    List<Chambre> findByBlocIdBlocAndTupeC(long idBloc, TypeChambre tupeC);
    @Query("Select c from Chambre c where c.bloc.idBloc=:idBloc and c.tupeC=:typeC")
    List<Chambre> getChambresParBlocEtTypeC(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);

    @Query("SELECT c FROM Chambre c " +
            "JOIN c.bloc b " +
            "JOIN b.foyers f " +
            "JOIN f.universite u " +
            "WHERE u.nomUniversite = :nomUniversite " +
            "AND c.tupeC = :type " +
            "AND NOT EXISTS (SELECT r FROM Reservation r " +
            "                WHERE r.anneeUniversitaire = :currentYear)")
    List<Chambre> findChambresNonReserveParNomUniversiteEtTypeC(
            @Param("nomUniversite") String nomUniversite,
            @Param("type") TypeChambre type,
            @Param("currentYear") Date currentYear);
}
