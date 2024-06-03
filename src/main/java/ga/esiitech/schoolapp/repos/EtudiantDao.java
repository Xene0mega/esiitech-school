package ga.esiitech.schoolapp.repos;

import ga.esiitech.schoolapp.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
}
