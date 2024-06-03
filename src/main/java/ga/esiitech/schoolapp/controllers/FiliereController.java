package ga.esiitech.schoolapp.controllers;
import ga.esiitech.schoolapp.entities.Filiere;
import ga.esiitech.schoolapp.services.FiliereService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/filieres")
@RestController
@AllArgsConstructor
public class FiliereController {

    private final FiliereService filiereService;

    @GetMapping("/getFiliereById/{idFiliere}")
    public Optional<Filiere> getFiliereById(@PathVariable Long idFiliere) {
        Optional<Filiere> filiere = filiereService.getFiliereById(idFiliere);
        return filiere;
    }

    @GetMapping("/getallFiliere")
    public List<Filiere> getAllFilieres() {
        List<Filiere> filieres = filiereService.getAllFiliere();
        return filieres;
    }

    @PostMapping("/creerFiliere")
    public Filiere creerFiliere(@RequestBody Filiere filiere) {
        Filiere newFiliere = filiereService.creerFiliere(filiere);
        return newFiliere;
    }

    @PostMapping("/{idFiliere}/ajouterMatiere/{idMatiere}")
    public Filiere ajoutMatiereFiliere(@PathVariable Long idFiliere, @PathVariable Long idMatiere) {
        Filiere filiere = filiereService.ajoutMatiereFiliere(idFiliere, idMatiere);
        return filiere;
    }

    @PostMapping("/{idFiliere}/ajoutEtudiant/{idEtudiant}")
    public Filiere ajoutEtudiantFiliere(@PathVariable Long idFiliere, @PathVariable Long idEtudiant) {
        Filiere filiere = filiereService.ajoutEtudiantFiliere(idFiliere, idEtudiant);
        return filiere;
    }

    @PutMapping("/modifierFiliere/{idFiliere}")
    public Filiere modifierFiliere(@PathVariable Long idFiliere, @RequestBody Filiere filiere) {
        Filiere newFiliere = filiereService.modifierFiliere(filiere, idFiliere);
        return newFiliere;
        }

    @DeleteMapping("/deleteFiliereById/{idFiliere}")
    public void deleteFiliereById(@PathVariable Long idFiliere) {
        filiereService.deleteFiliereById(idFiliere);
    }

    @DeleteMapping("/deleteAllFiliere")
    public void deleteAllFilieres() {
        filiereService.deleteAllFiliere();
    }
    }

