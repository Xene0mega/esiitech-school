package ga.esiitech.schoolapp.controllers;

import ga.esiitech.schoolapp.entities.Ecole;
import ga.esiitech.schoolapp.entities.Etudiant;
import ga.esiitech.schoolapp.services.EcoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/ecoles")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class EcoleController {

    @Autowired
    private EcoleService ecoleService;

    @PostMapping("/{idEcole}/filiere/{idFiliere}")
    public Ecole ajoutFiliereEcole(@PathVariable Long idEcole, @PathVariable Long idFiliere) {
        Ecole ecole = ecoleService.ajoutFiliereEcole(idEcole, idFiliere);
        return ecole;
    }

    @PostMapping("/{idEcole}/ajouterEtudiant")
    public Ecole ajouterEtudiantEcole(@PathVariable Long idEcole, @PathVariable Long idEtudiant) {
        Ecole ecole = ecoleService.ajouterEtudiantEcole(idEcole, idEtudiant);
        return ecole;
    }
    @PostMapping("/creerEcole")
    public String creerEcole(@RequestBody Ecole ecole) {
        ecoleService.creerEcole(ecole);
        return "Vous venez de créer l'école " + ecole.getNomEcole();
    }

    @GetMapping("/getAllEcole")
    public List<Ecole> getAllEcole() {
        return ecoleService.getAllEcole();
    }

    @GetMapping("/getEcoleById/{idEcole}")
    public Optional<Ecole> getEcoleById(@PathVariable Long idEcole) {
        Optional<Ecole> ecole = ecoleService.getEcoleById(idEcole);
        return ecole;
    }

    @DeleteMapping("/deleteEcoleById/{idecole}")
    public void deleteEcoleById(@PathVariable Long idEcole) {
        ecoleService.deleteEcoleById(idEcole);

    }

    @DeleteMapping("/deleteAllEcole")
    public void deleteAllEcole() {
        ecoleService.deleteAllEcole();
    }


}
