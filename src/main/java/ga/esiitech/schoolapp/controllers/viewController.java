package ga.esiitech.schoolapp.controllers;

import ga.esiitech.schoolapp.entities.Ecole;
import ga.esiitech.schoolapp.entities.Filiere;
import ga.esiitech.schoolapp.services.EcoleService;
import ga.esiitech.schoolapp.services.FiliereService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RequestMapping("/View")
@RestController
@AllArgsConstructor
public class viewController {

    @Autowired
    private final EcoleService ecoleService;

    @Autowired
    private final FiliereService filiereService;

    @GetMapping("/ecoleForm")
    public ModelAndView creerEcoleForm() {
        ModelAndView modelAndView = new ModelAndView("ecoleCreation");
        modelAndView.addObject("ecole", new Ecole());
        return modelAndView;
    }

    @PostMapping("/creerEcole")
    public ModelAndView creerEcole(Ecole ecole) {
        try {
            ecoleService.enregistrerEcole(ecole);
            return new ModelAndView("redirect:/View/getAllEcole");
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


    @GetMapping("/filiereForm/{idEcole}")
    public ModelAndView creerFiliereForm(@PathVariable Long idEcole) {
        ModelAndView modelAndView = new ModelAndView("filiereCreation");

        Optional<Ecole> ecoleOptional = ecoleService.getEcoleById(idEcole);
        if (ecoleOptional.isPresent()) {
            Ecole ecole = ecoleOptional.get();
            modelAndView.addObject("ecole", ecole);
        } else {
            modelAndView.setViewName("redirect:/erreur");
        }

        modelAndView.addObject("filiere", new Filiere());
        return modelAndView;
    }

    @PostMapping("/ajouterFiliere/{idEcole}")
    public ModelAndView ajouterFiliere(@PathVariable Long idEcole, @ModelAttribute Filiere filiere) {
        Optional<Ecole> ecoleOptional = ecoleService.getEcoleById(idEcole);
        if (ecoleOptional.isPresent()) {
            Ecole ecole = ecoleOptional.get();
            filiere.setEcole(ecole);
            filiereService.creerFiliere(filiere);
            return new ModelAndView("redirect:/View/getAllEcole");
        } else {
            return new ModelAndView("filiereCreation");
        }
    }

    @GetMapping("/supprimerEcole/{idEcole}")
    public ModelAndView supprimerEcole(@PathVariable Long idEcole) {
       try {
           ecoleService.deleteEcoleById(idEcole);
           return new ModelAndView("redirect:/View/getAllEcole");
       }catch(Exception e){
           return new ModelAndView("redirect:/View/getAllEcole");

        }

    }
    @GetMapping("/ecoleUpdateForm/{idEcole}")
    public ModelAndView modifierEcoleForm(@PathVariable Long idEcole){
        ModelAndView modelAndView = new ModelAndView("ecoleUpdate");

        Optional<Ecole> ecoleOptional = ecoleService.getEcoleById(idEcole);
        if (ecoleOptional.isPresent()) {
            Ecole ecole = ecoleOptional.get();
            modelAndView.addObject("ecole", ecole);
        } else {
            modelAndView.setViewName("redirect:/erreur");
        }
        return modelAndView;
    }

    @PostMapping("/ecoleUpdate/{idEcole}")
    public ModelAndView modifierEcole(@PathVariable Long idEcole, @ModelAttribute Ecole ecole){
        Optional<Ecole> ecoleOptional = ecoleService.getEcoleById(idEcole);
        if(ecoleOptional.isPresent()){
            Ecole ecoleUpdate =ecoleOptional.get();
            ecoleUpdate.setNomEcole(ecole.getNomEcole());
            ecoleUpdate.setAdresseEcole(ecole.getAdresseEcole());
            ecoleUpdate.setEmailEcole(ecole.getEmailEcole());
            ecoleUpdate.setNumeroTelephoneEcole(ecole.getNumeroTelephoneEcole());

            ecoleService.enregistrerEcole(ecoleUpdate);
            return new ModelAndView("redirect:/View/getAllEcole");
        }else{
            return new ModelAndView("redirect:/View/ecoleUpdate");
        }

    }
}