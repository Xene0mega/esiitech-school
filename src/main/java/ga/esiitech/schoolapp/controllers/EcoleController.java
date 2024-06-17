package ga.esiitech.schoolapp.controllers;

import ga.esiitech.schoolapp.entities.Ecole;
import ga.esiitech.schoolapp.services.EcoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Ecoles")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class EcoleController {

    @Autowired
    private EcoleService ecoleService;

    @PostMapping("/ajouterFiliere")
    public Ecole ajoutFiliereEcole(@PathVariable Long idEcole, @PathVariable Long idFiliere) {
        return ecoleService.ajoutFiliereEcole(idEcole, idFiliere);

    }

    @PostMapping("/ajouterEtudiant")
    public Ecole ajouterEtudiantEcole(@PathVariable Long idEcole, @PathVariable Long idEtudiant) {
        return ecoleService.ajouterEtudiantEcole(idEcole, idEtudiant);

    }
    @PostMapping("/creerEcole")
    public Ecole creerEcole(Ecole ecole) {
        return ecoleService.enregistrerEcole(ecole);

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
