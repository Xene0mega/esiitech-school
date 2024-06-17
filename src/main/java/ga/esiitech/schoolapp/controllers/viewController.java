package ga.esiitech.schoolapp.controllers;

import ga.esiitech.schoolapp.entities.Ecole;
import ga.esiitech.schoolapp.entities.Etudiant;
import ga.esiitech.schoolapp.entities.Filiere;
import ga.esiitech.schoolapp.entities.Matiere;
import ga.esiitech.schoolapp.services.EcoleService;
import ga.esiitech.schoolapp.services.EtudiantService;
import ga.esiitech.schoolapp.services.FiliereService;
import ga.esiitech.schoolapp.services.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/View")
@RestController
@AllArgsConstructor
public class viewController {

    @Autowired
    private final EcoleService ecoleService;

    @Autowired
    private final FiliereService filiereService;

    @Autowired
    private final MatiereService matiereService;

    @Autowired
    private final EtudiantService etudiantService;

    //Ce controller va permettre de genrer la premiere vue de mon programme en tapant sur l'url localhost:8080/getAllEcole
    @GetMapping("/getAllEcole")
    public ModelAndView getAllEcole() {
        ModelAndView modelAndView = new ModelAndView("ecoleListe");
        modelAndView.addObject("ecoles", ecoleService.getAllEcole());
        return modelAndView;
    }

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

    /*@GetMapping("/getAllFiliere/{idEcole}")
public ModelAndView getAllFiliere(@PathVariable Long idEcole) {
    Ecole ecole = ecoleService.findById(idEcole).orElseThrow();
    List<Filiere> filieres = ecole.getFilieres();
    ModelAndView modelAndView = new ModelAndView("ecoleView");
    modelAndView.addObject("filieres", filieres);
    return modelAndView;
}*/
    @GetMapping("/afficherFiliereEcole/{idEcole}")
    public ModelAndView afficherFiliereEcole(@PathVariable Long idEcole) {
        ModelAndView modelAndView = new ModelAndView("filiereListe");
        try{
            Ecole ecole = ecoleService.findAllFiliereEcole(idEcole);
            modelAndView.addObject("ecole", ecole);
            modelAndView.addObject("filieres", ecole.getFilieres() );

        }catch (IllegalArgumentException e){
            modelAndView.setViewName("redirect:/erreur");
        }
        return modelAndView;
        }
    @GetMapping("/matiereForm/{idEcole}/{idFiliere}")
    public ModelAndView ajouterMatiereFiliereEcole(@PathVariable Long idEcole, @PathVariable Long idFiliere ){
            ModelAndView modelAndView = new ModelAndView("matiereCreation");

            Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
            Filiere filiere = filiereService.getFiliereById(idFiliere).orElseThrow();
            modelAndView.addObject("ecole", ecole);
            modelAndView.addObject("filiere", filiere);
            modelAndView.addObject("matiere", new Matiere());
            return modelAndView;
        }

    @PostMapping("/ajouterMatiereFiliereEcole/{idEcole}/{idFiliere}")
    public ModelAndView ajouterMatiereFiliereEcole(@PathVariable Long idEcole, @PathVariable Long idFiliere, @ModelAttribute Matiere matiere) {
        Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
        Filiere filiere = filiereService.getFiliereById(idFiliere).orElseThrow();
        matiere.setEcole(ecole);
        matiere.setFiliere(filiere);
        matiereService.creerMatiere(matiere);
       return new ModelAndView("redirect:/View/afficherFiliereEcole/{idEcole}");

    }
    @GetMapping("/afficherMatiereFiliereEcole/{idEcole}/{idFiliere}")
    public ModelAndView afficherMatiereFiliereEcole(@PathVariable Long idEcole,@PathVariable Long idFiliere){
        ModelAndView modelAndView = new ModelAndView("matiereListe");
        try{
            Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
            Filiere filiere = filiereService.findAllMatiereFiliere(idFiliere);
            modelAndView.addObject("ecole", ecole);
            modelAndView.addObject("filiere", filiere);
            modelAndView.addObject("matieres", filiere.getMatieres() );

        }catch (IllegalArgumentException e){
            modelAndView.setViewName("redirect:/erreur");
        }
        return modelAndView;

    }
    @GetMapping("/supprimerFiliere/{idEcole}/{idFiliere}")
    public ModelAndView supprimerFiliere(@PathVariable Long idEcole, @PathVariable Long idFiliere) {
        try {
            filiereService.deleteFiliereById(idFiliere);
            return new ModelAndView("redirect:/View/afficherFiliereEcole/"+idEcole);
        }catch(Exception e){
            return new ModelAndView("redirect:/View/afficherFiliereEcole/"+idEcole);

        }

    }
    @GetMapping("/filiereUpdateForm/{idEcole}/{idFiliere}")
    public ModelAndView modifierFiliereForm(@PathVariable Long idEcole, @PathVariable Long idFiliere){

        ModelAndView modelAndView = new ModelAndView("filiereUpdate");
        Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
        Filiere filiere = filiereService.getFiliereById(idFiliere).orElseThrow();
            modelAndView.addObject("ecole", ecole);
            modelAndView.addObject("filiere", filiere);

        return modelAndView;
    }

    @PostMapping("/filiereUpdate/{idEcole}/{idFiliere}")
    public ModelAndView modifierFiliere(@PathVariable Long idEcole, @PathVariable Long idFiliere, @ModelAttribute Filiere filiere){
        Filiere filiereExistant = filiereService.getFiliereById(idFiliere).orElseThrow();
            filiereExistant.setNomFiliere(filiere.getNomFiliere());
            filiereService.creerFiliere(filiereExistant);
            return new ModelAndView("redirect:/View/afficherFiliereEcole/"+ idEcole);
    }
    @GetMapping("/etudiantForm/{idEcole}/{idFiliere}")
    public ModelAndView ajouterEtudiantFiliereEcole(@PathVariable Long idEcole, @PathVariable Long idFiliere){
        ModelAndView modelAndView = new ModelAndView("etudiantCreation");

        Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
        Filiere filiere = filiereService.getFiliereById(idFiliere).orElseThrow();
        modelAndView.addObject("ecole", ecole);
        modelAndView.addObject("filiere", filiere);
        modelAndView.addObject("etudiant", new Etudiant());
        return modelAndView;
    }
    @PostMapping("/ajouterEtudiantFiliereEcole/{idEcole}/{idfiliere}")
    public  ModelAndView ajouterEtudiantFilereEcole(@PathVariable Long idEcole, @PathVariable Long idfiliere,  @ModelAttribute Etudiant etudiant){

        Ecole ecole =ecoleService.getEcoleById(idEcole).orElseThrow();
        Filiere filiere =filiereService.getFiliereById(idfiliere).orElseThrow();
        etudiant.setEcole(ecole);
        etudiant.setFiliere(filiere);
        etudiantService.creerEtudiant(etudiant);
        return new ModelAndView("redirect:/View/afficherFiliereEcole/"+ idEcole);

    }
    @GetMapping("/afficherEtudiantFiliereEcole/{idEcole}/{idFiliere}")
    public ModelAndView afficherEtudiantFiliereEcole(@PathVariable Long idEcole,@PathVariable Long idFiliere){
        ModelAndView modelAndView = new ModelAndView("etudiantListe");
        try{
            Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
            Filiere filiere = filiereService.findAllEtudiantFiliere(idFiliere);
            modelAndView.addObject("ecole", ecole);
            modelAndView.addObject("filiere", filiere);
            modelAndView.addObject("etudiants", filiere.getEtudiants() );

        }catch (IllegalArgumentException e){
            modelAndView.setViewName("redirect:/erreur");
        }
        return modelAndView;

    }
    @GetMapping("/matiereUpdateForm/{idEcole}/{idFiliere}/{idMatiere}")
    public ModelAndView modifierMatiereForm(@PathVariable Long idEcole, @PathVariable Long idFiliere, @PathVariable Long idMatiere){

        ModelAndView modelAndView = new ModelAndView("matiereUpdate");
        Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
        Filiere filiere = filiereService.getFiliereById(idFiliere).orElseThrow();
        Matiere matiere = matiereService.getMatiereById(idMatiere).orElseThrow();

        modelAndView.addObject("ecole", ecole);
        modelAndView.addObject("filiere", filiere);
        modelAndView.addObject("matiere", matiere);

        return modelAndView;
    }

    @PostMapping("/matiereUpdate/{idEcole}/{idFiliere}/{idMatiere}")
    public ModelAndView modifierMatiere(@PathVariable Long idEcole, @PathVariable Long idFiliere, @PathVariable Long idMatiere, @ModelAttribute Matiere matiere){
        Matiere matiereExistant = matiereService.getMatiereById(idMatiere).orElseThrow();
        matiereExistant.setNomMatiere(matiere.getNomMatiere());
        matiereExistant.setCoefficientMatiere(matiere.getCoefficientMatiere());
        matiereExistant.setVolumeHoraireMatiere(matiere.getVolumeHoraireMatiere());
        matiereService.creerMatiere(matiereExistant);
        return new ModelAndView("redirect:/View/afficherMatiereFiliereEcole/"+ idEcole+"/"+idFiliere);
    }

    @GetMapping("/supprimerMatiere/{idEcole}/{idFiliere}/{idMatiere}")
    public ModelAndView supprimerFiliere(@PathVariable Long idEcole, @PathVariable Long idFiliere, @PathVariable Long idMatiere) {
        try {
            matiereService.deleteMatiereById(idMatiere);
            return new ModelAndView("redirect:/View/afficherMatiereFiliereEcole/" + idEcole + "/" + idFiliere);
        } catch (Exception e) {
            return new ModelAndView("redirect:/View/afficherMatiereFiliereEcole/" + idEcole + "/" + idFiliere);

        }
    }
    @GetMapping("/etudiantUpdateForm/{idEcole}/{idFiliere}/{idEtudiant}")
    public ModelAndView modifierEtudiantForm(@PathVariable Long idEcole, @PathVariable Long idFiliere, @PathVariable Long idEtudiant){

        ModelAndView modelAndView = new ModelAndView("etudiantUpdate");
        Ecole ecole = ecoleService.getEcoleById(idEcole).orElseThrow();
        Filiere filiere = filiereService.getFiliereById(idFiliere).orElseThrow();
        Etudiant etudiant= etudiantService.getEtudiantById(idEtudiant).orElseThrow();

        modelAndView.addObject("ecole", ecole);
        modelAndView.addObject("filiere", filiere);
        modelAndView.addObject("etudiant", etudiant);

        return modelAndView;
    }

    @PostMapping("/etudiantUpdate/{idEcole}/{idFiliere}/{idEtudiant}")
    public ModelAndView modifierEtudiant(@PathVariable Long idEcole, @PathVariable Long idFiliere, @PathVariable Long idEtudiant, @ModelAttribute Etudiant etudiant){
        Etudiant etudiantExistant = etudiantService.getEtudiantById(idEtudiant).orElseThrow();
        etudiantExistant.setNomEtudiant(etudiant.getNomEtudiant());
        etudiantExistant.setPrenomEtudiant(etudiant.getPrenomEtudiant());
        etudiantExistant.setAgeEtudiant(etudiant.getAgeEtudiant());
        etudiantExistant.setGenreEtudiant(etudiant.getGenreEtudiant());
        etudiantExistant.setNumeroTelephoneEtudiant(etudiant.getNumeroTelephoneEtudiant());
        etudiantExistant.setEmailEtudiant(etudiant.getEmailEtudiant());
        etudiantExistant.setAdresseEtudiant(etudiant.getAdresseEtudiant());
        etudiantExistant.setQuartierEtudiant(etudiant.getQuartierEtudiant());
        etudiantService.creerEtudiant(etudiantExistant);

        return new ModelAndView("redirect:/View/afficherEtudiantFiliereEcole/"+ idEcole+"/"+idFiliere);
    }

    @GetMapping("/supprimerEtudiant/{idEcole}/{idFiliere}/{idEtudiant}")
    public ModelAndView supprimerEtudiant(@PathVariable Long idEcole, @PathVariable Long idFiliere, @PathVariable Long idEtudiant) {
        try {
            etudiantService.deleteEtudiantById(idEtudiant);
            return new ModelAndView("redirect:/View/afficherEtudiantFiliereEcole/" + idEcole + "/" + idFiliere);
        } catch (Exception e) {
            return new ModelAndView("redirect:/View/afficherEtudiantFiliereEcole/" + idEcole + "/" + idFiliere);

        }
    }


    }
