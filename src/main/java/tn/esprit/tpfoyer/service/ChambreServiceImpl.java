package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {
    @Autowired
     ChambreRepository chambreRepository;
    UniversiteRepository universiteRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();

    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        System.out.println("Chambre reçue : " + c.getNumeroChambre() + ", " + c.getTupeC());
        return chambreRepository.save(c);

    }

    @Override
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);


    }

    @Override
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);

    }


    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre tupeC) {
        return chambreRepository.findByBlocIdBlocAndTupeC(idBloc, tupeC);
    }

    @Override
    public List<Chambre> getChambreNonReservesParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre typeC) {
        Date currentYear = new Date(System.currentTimeMillis());
        return chambreRepository.findChambresNonReserveParNomUniversiteEtTypeC(nomUniversite,typeC,currentYear);
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        Universite u=universiteRepository.findByNomWithFoyerAndBlocs(nomUniversite);
        if(u==null){
            throw new RuntimeException("Universite non existante");
        }
        if(u.getFoyer()==null){
            throw new RuntimeException("Foyer non existante");
        }
        return u.getFoyer().getBloc().stream().flatMap(bloc->bloc.getChambres().stream()).collect(Collectors.toList());
    }
}
