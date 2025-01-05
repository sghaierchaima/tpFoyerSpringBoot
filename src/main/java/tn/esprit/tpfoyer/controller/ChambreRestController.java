package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")

public class ChambreRestController {
    @Autowired
   private  IChambreService chambreService;
    // http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }
    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }
    // http://localhost:8089/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }
    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }
    // http://localhost:8089/tpfoyer/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }
    @Operation(description="Récuperer des chambres par son bloc et type")
    @GetMapping("getChambresParBlocEtType/bloc/{idBloc}/type/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable("idBloc") long idBloc, @PathVariable("typeC") TypeChambre tupeC) {
        return chambreService.getChambresParBlocEtType(idBloc,tupeC);
    }



    @Operation(description = "Récuperer des chambres non resérvés par leur nom d'université et leur type de chambre")
    @GetMapping("/getChambresNonReserveesParNomUniversiteEtTypeChambre/{nomUniversite}/{typeC}")
    public List<Chambre> getChambresNonReserveesParNomUniversiteEtTypeChambre(@PathVariable("nomUniversite") String nomUniversite,@PathVariable("typeC") TypeChambre typeC){
        return chambreService.getChambreNonReservesParNomUniversiteEtTypeChambre(nomUniversite,typeC);
    }

    @DeleteMapping("/delete-chambre/{idChambre}")
    public void deleteChambre(@PathVariable("idChambre") Long idChambre) {
        chambreService.deleteChambre(idChambre);
    }

    @Operation(description="Recupérer des chambres par leur nom de l'université")
    @GetMapping("/getChambresParNomUniversitaire/{nomUniversite}")
    public List<Chambre> getChambresParNomUniversitaire(@PathVariable("nomUniversite") String nomUniversite){
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }

}
