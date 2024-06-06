package ga.esiitech.schoolapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "ECOLE")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEcole;

    @Column(name = "nomEcole", length = 20, nullable = false)
    private String nomEcole;

    @Column(name = "adresseEcole", length = 55, nullable = false)
    private String adresseEcole;

    @Column(name = "numeroTelephoneEcole", length = 10, unique = true, nullable = false)
    private String numeroTelephoneEcole;

    @Column(name = "emailEcole", length = 30, nullable = false, unique = true)
    private String emailEcole;

    @OneToMany(mappedBy = "ecole")
    private List<Filiere> filieres;

    @OneToMany(mappedBy = "ecole")
    private List<Etudiant> etudiants;
}