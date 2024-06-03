package ga.esiitech.schoolapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Table(name="etudiant")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEtudiant;

    @Column(name="nomEtudiant",length = 30, nullable = false)
    private String nomEtudiant;

    @Column(name="prenomEtudiant",length = 30, nullable = false)
    private String prenomEtudiant;

    @Column(name="ageEtudiant",length = 5, nullable = false)
    private int ageEtudiant;

    @Column(name="genreEtudiant",length = 5, nullable = false)
    private String genreEtudiant;

    @Column(name="numeroTelephoneEtudiant",length = 10, nullable = false, unique = true)
    private String numeroTelephoneEtudiant;

    @Column(name="emailEtudiant",length = 20, nullable = false, unique = true)
    private String emailEtudiant;

    @Column(name="adresseEtudiant",length = 30, nullable = false)
    private String adressseEtudiant;

    @Column(name="quartierEtudiant",length = 30, nullable = false)
    private String quartierEtudiant;

    @ManyToOne
    @JoinColumn(name = "ECOLE_ID", referencedColumnName = "idEcole")
    private Ecole ecole;

    @ManyToOne
    @JoinColumn(name = "FILIERE_ID", referencedColumnName = "idFiliere")
    private Filiere filiere;

}
