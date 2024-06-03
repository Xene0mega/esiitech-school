package ga.esiitech.schoolapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Table(name = "filiere")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFiliere;

    private String nomFiliere;

    @OneToMany(mappedBy = "filiere")
    private List<Matiere> matieres;

    @ManyToOne
    @JoinColumn(name = "ECOLE_ID", referencedColumnName = "idEcole")
    private Ecole ecole;

    @OneToMany(mappedBy = "filiere")
    private List<Etudiant> etudiants;
}
