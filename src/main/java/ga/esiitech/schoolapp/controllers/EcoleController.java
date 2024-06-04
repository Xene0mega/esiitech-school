package ga.esiitech.schoolapp.controllers;

import ga.esiitech.schoolapp.entities.Ecole;
import ga.esiitech.schoolapp.services.EcoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RequestMapping("/Ecoles")
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
    @GetMapping("/ecoleForm")
    public ModelAndView creerEcoleForm() {
        ModelAndView modelAndView = new ModelAndView("ecoleCreation");
        modelAndView.addObject("ecole",   new Ecole());
        return modelAndView;
    }
    @PostMapping("/creerEcole")
    public ModelAndView creerEcole(Ecole ecole) {
        try {
            ecoleService.creerEcole(ecole);
            ModelAndView modelAndView = new ModelAndView("redirect:/Ecoles/getAllEcole");
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("ecoleCreation");
        }
    }

    @GetMapping("/getAllEcole")
    public ModelAndView getAllEcole() {
        ModelAndView modelAndView = new ModelAndView("ecoleListe");
        modelAndView.addObject("ecoles", ecoleService.getAllEcole());
       return modelAndView;
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
