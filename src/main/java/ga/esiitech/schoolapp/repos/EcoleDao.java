package ga.esiitech.schoolapp.repos;

import ga.esiitech.schoolapp.entities.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoleDao extends JpaRepository<Ecole, Long> {

}

