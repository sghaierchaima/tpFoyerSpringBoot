package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class UniversiteServiceImpli implements IUniversiteService{
    @Autowired
   private UniversiteRepository universiteRepository;
    private FoyerRepository foyerRepository;
    @Override
    public List<Universite> retrieveAllUniversities() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).isPresent()? universiteRepository.findById(idUniversite).get():null;
    }

    @Override
    public Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite) {
        Foyer f=foyerRepository.findById(idFoyer).orElse(null);
        Universite u=universiteRepository.findByNomUniversite(nomUniversite).orElse(null);
        if(f.getUniversite() != null || u.getFoyer() != null) {
            throw new RuntimeException("Association déjà existant pour cet foyer et cette université");
        }
        u.setFoyer(f);
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite u=universiteRepository.findById(idUniversite).orElse(null);
        if(u.getFoyer() == null) {
            throw new RuntimeException("Aucun foyer  dans l'université");
        }
        Foyer f=u.getFoyer();
        u.setFoyer(null);
        universiteRepository.save(u);
        return u;
    }

}
