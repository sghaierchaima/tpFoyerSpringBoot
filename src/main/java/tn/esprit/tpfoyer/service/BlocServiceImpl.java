package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {
    @Autowired
    private BlocRepository blocRepository;
    ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveAllBloc() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId).get();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambres, long idBloc) {
        Bloc b=blocRepository.findById(idBloc).orElse(null);
        Set<Chambre> chambres=chambreRepository.findAllByNumeroChambreIn(numChambres);
        if(chambres.size()!=numChambres.size()){
            throw new RuntimeException("Une ou plusieurs chambres sont introuvables");
        }
        for(Chambre c:chambres){
            if(c.getBloc()!=null && c.getBloc().getIdBloc()!=idBloc) {
                throw new RuntimeException("Le chambre "+c.getNumeroChambre()+" est déjà affecté dans un autre bloc");
            }
        }
        for(Chambre c:chambres){
            c.setBloc(b);
        }
        assert b != null;
        b.getChambres().addAll(chambres);
        blocRepository.save(b);
        chambreRepository.saveAll(chambres);
        return b;
    }
}
