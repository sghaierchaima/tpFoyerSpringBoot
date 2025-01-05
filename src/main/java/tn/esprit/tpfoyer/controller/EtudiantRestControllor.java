package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestControllor {
    @Autowired
    private  IEtudiantService etudiantService;

    //http://localhost:8089/foyer/etudiant/retrieve-all-etudiants
    @Operation(description = "Récuperer tous les étudiants")
    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    //http://localhost:8089/foyer/etudiant/add-etudiants
    @Operation(description="Ajouter des étudiants")
    @PostMapping("/add-etudiants")
    public List<Etudiant> addEtudiant(@RequestBody List<Etudiant> etudiants) {
        return etudiantService.addEtudiants(etudiants);
    }
    //http://localhost:8089/foyer/etudiant/retrieve-etudiant/4
    @Operation(description="Recupérer un étudiant par son ID")
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable ("etudiant-id") Long etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    //http://localhost:8089/foyer/etudiant/update-etudiant
    @Operation(description="modifier un étudiant")
    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(etudiant);
    }
    //http://localhost:8089/foyer/etudiant/removeEtudiant/1
    @Operation(description = "Supprimer un étudiant par son ID")
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        etudiantService.removeEtudiant(etudiantId);
    }

}
