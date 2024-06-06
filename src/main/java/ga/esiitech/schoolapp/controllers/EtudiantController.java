package ga.esiitech.schoolapp.controllers;
import ga.esiitech.schoolapp.entities.Etudiant;
import ga.esiitech.schoolapp.services.EtudiantService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Etudiants")
@RestController
@AllArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    @PostMapping("/creerEtudiant")
    public Etudiant creerEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.creerEtudiant(etudiant);
    }

    @GetMapping("/getEtudiantById/{idEtudiant}")
    public Optional<Etudiant> getEtudiantById(@PathVariable Long idEtudiant) {
        return etudiantService.getEtudiantById(idEtudiant);
    }

    @GetMapping("/getAllEtudiant")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiant();
    }

    @PutMapping("/modifierEtudiant/{idEtudiant}")
    public Etudiant modifierEtudiant(@PathVariable Long idEtudiant, @RequestBody Etudiant etudiant) {
        return etudiantService.modifierEtudiant(etudiant, idEtudiant);
    }

    @DeleteMapping("/deleteEtudiantById/{idEtudiant}")
    public void deleteEtudiantById(@PathVariable Long idEtudiant) {
        etudiantService.deleteEtudiantById(idEtudiant);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllEtudiants() {
        etudiantService.deleteAllEtudiant();
    }
}

