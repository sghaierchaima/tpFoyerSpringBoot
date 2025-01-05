package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/university")
public class UniversityRestController {
    @Autowired
   IUniversiteService universiteService;

    @Operation(description="Récupérer tous les universités")
    @GetMapping("/retrieve-all-universites")
    public List<Universite> retrieveAllUniversites() {
        return universiteService.retrieveAllUniversities();
    }

}
