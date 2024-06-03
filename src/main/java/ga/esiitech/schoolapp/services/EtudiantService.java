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
public class EtudiantService {

        private final EtudiantDao etudiantDao;

    public Optional<Etudiant> getEtudiantById(Long idEtudiant) {

        return etudiantDao.findById(idEtudiant);
    }
    public List<Etudiant> getAllEtudiant(){
        return etudiantDao.findAll();
    }

    public Etudiant creerEtudiant(Etudiant etudiant) {

        return etudiantDao.save(etudiant);
    }

        public Etudiant modifierEtudiant(Etudiant etudiant, Long IdEtudiant) {

            Optional<Etudiant> etudiantExistant = etudiantDao.findById(IdEtudiant);
            if (etudiantExistant.isPresent()) {
                Etudiant newEtudiant = etudiantExistant.get();
                newEtudiant.setNomEtudiant(etudiant.getNomEtudiant());
                newEtudiant.setPrenomEtudiant(etudiant.getPrenomEtudiant());
                newEtudiant.setGenreEtudiant(etudiant.getGenreEtudiant());
                newEtudiant.setAgeEtudiant(etudiant.getAgeEtudiant());
                newEtudiant.setEmailEtudiant(etudiant.getEmailEtudiant());
                newEtudiant.setAdressseEtudiant(etudiant.getAdressseEtudiant());
                newEtudiant.setNumeroTelephoneEtudiant(etudiant.getNumeroTelephoneEtudiant());
                newEtudiant.setQuartierEtudiant(etudiant.getQuartierEtudiant());
                newEtudiant.setFiliere(etudiant.getFiliere());
                newEtudiant.setEcole(etudiant.getEcole());

                return etudiantDao.save(newEtudiant);

            }
            return null;
        }
        public void deleteEtudiantById(Long idEtudiant) {
            etudiantDao.deleteById(idEtudiant);
        }

        public void deleteAllEtudiant(){
            etudiantDao.deleteAll();
    }

}
