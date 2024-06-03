package ga.esiitech.schoolapp.services;

import ga.esiitech.schoolapp.entities.Matiere;
import ga.esiitech.schoolapp.repos.MatiereDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MatiereService {

    private final MatiereDao matiereDao;

    public Matiere creerMatiere(Matiere matiere) {
        return matiereDao.save(matiere);
    }
    public Optional<Matiere> getMatiereById(Long idMatiere){

        return matiereDao.findById(idMatiere);
    }

    public List<Matiere> getAllMatieres() {

        return matiereDao.findAll();
    }
    public Matiere modifierMatiere(Matiere matiere, Long idMatiere) {
        Optional<Matiere> matiereExistant = matiereDao.findById(idMatiere);
        if (matiereExistant.isPresent()) {
            Matiere newMatiere = matiereExistant.get();
            newMatiere.setNomMatiere(matiere.getNomMatiere());
            newMatiere.setCoefficientMatiere(matiere.getCoefficientMatiere());
            newMatiere.setVolumeHoraireMatiere(matiere.getVolumeHoraireMatiere());
            newMatiere.setFiliere(matiere.getFiliere());

        }
        return null;
    }


    public void deleteMatiereById(Long idMatiere) {

        matiereDao.deleteById(idMatiere);
    }
    public void deleteAllMatiere(){
        matiereDao.deleteAll();
    }

}
