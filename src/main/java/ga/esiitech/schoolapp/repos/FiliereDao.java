package ga.esiitech.schoolapp.repos;

import ga.esiitech.schoolapp.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereDao extends JpaRepository<Filiere, Long> {
}
