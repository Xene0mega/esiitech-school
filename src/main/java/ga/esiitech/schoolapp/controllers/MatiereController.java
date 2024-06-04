package ga.esiitech.schoolapp.controllers;

import ga.esiitech.schoolapp.entities.Matiere;
import ga.esiitech.schoolapp.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Matieres")
@RestController
@AllArgsConstructor
public class MatiereController {

    private final MatiereService matiereService;

    @PostMapping("/creerMatiere")
    public Matiere creerMatiere(@RequestBody Matiere matiere) {
        return matiereService.creerMatiere(matiere);
    }

    @GetMapping("/{idMatiere}")
    public Optional<Matiere> getMatiereById(@PathVariable Long idMatiere) {
        return matiereService.getMatiereById(idMatiere);
    }

    @GetMapping("/getAllMatiere")
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @PutMapping("/modifierMatiere/{idMatiere}")
    public Matiere modifierMatiere(@PathVariable Long idMatiere, @RequestBody Matiere matiere) {
        return matiereService.modifierMatiere(matiere, idMatiere);
    }

    @DeleteMapping("/deleteMatiereById/{idMatiere}")
    public void deleteMatiereById(@PathVariable Long idMatiere) {
        matiereService.deleteMatiereById(idMatiere);
    }

    @DeleteMapping("/deleteAllMatiere")
    public void deleteAllMatieres() {
        matiereService.deleteAllMatiere();
    }
}

