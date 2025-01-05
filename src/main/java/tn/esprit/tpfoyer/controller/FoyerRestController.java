package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {
    @Autowired
 IFoyerService foyerService;

    @Operation(description = "Récuperer tous les foyers")
    @GetMapping("/retrives-all-foyers")
    public List<Foyer> getFoyers(){
        List<Foyer> listFoyer = foyerService.retrieveAllFoyers();
        return listFoyer;
    }

    @Operation(description="Ajouter des foyers")
    @PostMapping("/add-foyer")
    public Foyer addChambre(@RequestBody Foyer f) {
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    @Operation(description = "Supprimer un foyer par son ID")
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long fId) {
        foyerService.removeFoyer(fId);
    }
    //http://localhost:8089/foyer/foyerU/modify-foyer
    @Operation(description = "Modifier un foyer")
    @PutMapping("/update-foyer")
    public Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.updateFoyer(foyer);
    }
}
