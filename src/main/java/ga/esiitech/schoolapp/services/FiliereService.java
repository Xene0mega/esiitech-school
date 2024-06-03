package ga.esiitech.schoolapp.services;

import ga.esiitech.schoolapp.entities.Etudiant;
import ga.esiitech.schoolapp.entities.Filiere;
import ga.esiitech.schoolapp.entities.Matiere;
import ga.esiitech.schoolapp.repos.EtudiantDao;
import ga.esiitech.schoolapp.repos.FiliereDao;
import ga.esiitech.schoolapp.repos.MatiereDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiliereService {

    private  final FiliereDao filiereDao;
    private final MatiereDao matiereDao;

    private EtudiantDao etudiantDao;

    public Optional<Filiere> getFiliereById(Long idFiliere) {

        return filiereDao.findById(idFiliere);
    }
    public List<Filiere> getAllFiliere(){
        return filiereDao.findAll();
    }

    public Filiere creerFiliere(Filiere filiere) {

        return filiereDao.save(filiere);
    }

    public Filiere ajoutMatiereFiliere(Long idFiliere, Long idMatiere) {

        Filiere filiere = filiereDao.findById(idFiliere).orElse(null);
        Matiere matiere = matiereDao.findById(idMatiere).orElse(null);

        if(filiere!= null && matiere!= null) {
            filiere.getMatieres().add(matiere);

          filiereDao.save(filiere);
        }else {
            throw new IllegalArgumentException("filiere ou matiere non trouvé");
        }
        return filiere;

    }

    public Filiere ajoutEtudiantFiliere(Long idFiliere, Long idEtudiant) {
        Filiere filiere = filiereDao.findById(idFiliere).orElse(null);
        Etudiant etudiant = etudiantDao.findById(idEtudiant).orElse(null);

        if (filiere != null && etudiant != null) {
            filiere.getEtudiants().add(etudiant);

            filiereDao.save(filiere);
        } else {throw new IllegalArgumentException("Filière ou étudiant non trouvé");
        }
        return filiere;

     }


        public Filiere modifierFiliere(Filiere filiere, Long IdFiliere) {

        Optional<Filiere> filiereExistant = filiereDao.findById(IdFiliere);
        if (filiereExistant.isPresent()) {
            Filiere newFiliere = filiereExistant.get();
            newFiliere.setNomFiliere(filiere.getNomFiliere());
            newFiliere.setMatieres(filiere.getMatieres());
            newFiliere.setEcole(filiere.getEcole());
            newFiliere.setEtudiants(filiere.getEtudiants());

            return filiereDao.save(newFiliere);

        }
        return null;
    }
    public void deleteFiliereById(Long idFiliere) {
        filiereDao.deleteById(idFiliere);
    }

    public void deleteAllFiliere(){
        filiereDao.deleteAll();
    }
}
