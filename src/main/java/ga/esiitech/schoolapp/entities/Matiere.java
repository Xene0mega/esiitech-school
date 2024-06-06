package ga.esiitech.schoolapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Table( name = "matiere")
@Entity

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatiere;

    @Column(name = "nomMatiere", length = 20, nullable = false, unique = true)
    private String nomMatiere;

    @Column(name = "coefficientMatiere", length = 5, nullable = false)
    private int coefficientMatiere;

    @Column(name = "volumeHoraireMatiere", length = 5, nullable = false)
    private String  volumeHoraireMatiere;

    @ManyToOne
    @JoinColumn(name="FILIERE_ID", referencedColumnName="idFiliere")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name="ECOLE_ID", referencedColumnName="idEcole")
    private Ecole ecole;
}
