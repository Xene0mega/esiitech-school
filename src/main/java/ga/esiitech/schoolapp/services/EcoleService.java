package ga.esiitech.schoolapp.services;

import ga.esiitech.schoolapp.entities.Ecole;
import ga.esiitech.schoolapp.entities.Etudiant;
import ga.esiitech.schoolapp.entities.Filiere;
import ga.esiitech.schoolapp.repos.EcoleDao;
import ga.esiitech.schoolapp.repos.EtudiantDao;
import ga.esiitech.schoolapp.repos.FiliereDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EcoleService{

    private final EcoleDao ecoleDao;
    private final FiliereDao filiereDao;
    private final EtudiantDao etudiantDao;

    public Ecole enregistrerEcole(Ecole ecole) {
        return ecoleDao.save(ecole);
    }

    public Ecole ajoutFiliereEcole(Long idEcole, Long idFiliere) {
        Ecole ecole = ecoleDao.findById(idEcole).orElse(null);
        Filiere filiere = filiereDao.findById(idFiliere).orElse(null);

        if (ecole != null && filiere != null) {
            ecole.getFilieres().add(filiere);
            ecoleDao.save(ecole);
        } else {
            throw new IllegalArgumentException("École ou filière non trouvée");
        }
        return ecole;
    }

    public Ecole ajouterEtudiantEcole(Long idEcole, Long idEtudiant) {
        Ecole ecole = ecoleDao.findById(idEcole).orElse(null);
        Etudiant etudiant = etudiantDao.findById(idEtudiant).orElse(null);
        if (ecole != null && etudiant!=null) {
            ecole.getEtudiants().add(etudiant);
            ecoleDao.save(ecole);
        } else {
            throw new IllegalArgumentException("École non trouvée");
        }
        return ecole;
    }
    public Ecole findAllFiliereEcole(Long idEcole){
        Ecole ecole = ecoleDao.findById(idEcole).orElse(null);
        if(ecole!= null){
             ecole.getFilieres();
        }else {
            throw new IllegalArgumentException("Ecole non trouvé avec ID"+ idEcole);
        }
        return ecole;
    }

    public List<Ecole> getAllEcole() {
        return ecoleDao.findAll();
    }

    public Optional<Ecole> getEcoleById(Long idEcole) {
        return ecoleDao.findById(idEcole);
    }

    public void deleteEcoleById(Long idEcole) {
        ecoleDao.deleteById(idEcole);
    }

    public void deleteAllEcole() {
        ecoleDao.deleteAll();
    }
}
