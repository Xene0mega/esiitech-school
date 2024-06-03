package ga.esiitech.schoolapp.repos;

import ga.esiitech.schoolapp.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereDao extends JpaRepository<Matiere, Long> {
}
