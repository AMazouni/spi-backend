package fr.ubo.spibackend.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "FORMATION", schema = "DOSI", catalog = "")
public class FormationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;
    @Basic
    @Column(name = "DIPLOME", nullable = false, length = 3)
    private String diplome;
    @Basic
    @Column(name = "N0_ANNEE", nullable = false, precision = 0)
    private boolean n0Annee;
    @Basic
    @Column(name = "NOM_FORMATION", nullable = false, length = 64)
    private String nomFormation;
    @Basic
    @Column(name = "DOUBLE_DIPLOME", nullable = false, length = 1)
    private String doubleDiplome;
    @Basic
    @Column(name = "DEBUT_ACCREDITATION", nullable = true)
    private Date debutAccreditation;
    @Basic
    @Column(name = "FIN_ACCREDITATION", nullable = true)
    private Date finAccreditation;

    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public boolean isN0Annee() {
        return n0Annee;
    }

    public void setN0Annee(boolean n0Annee) {
        this.n0Annee = n0Annee;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public String getDoubleDiplome() {
        return doubleDiplome;
    }

    public void setDoubleDiplome(String doubleDiplome) {
        this.doubleDiplome = doubleDiplome;
    }

    public Date getDebutAccreditation() {
        return debutAccreditation;
    }

    public void setDebutAccreditation(Date debutAccreditation) {
        this.debutAccreditation = debutAccreditation;
    }

    public Date getFinAccreditation() {
        return finAccreditation;
    }

    public void setFinAccreditation(Date finAccreditation) {
        this.finAccreditation = finAccreditation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormationEntity that = (FormationEntity) o;
        return n0Annee == that.n0Annee && Objects.equals(codeFormation, that.codeFormation) && Objects.equals(diplome, that.diplome) && Objects.equals(nomFormation, that.nomFormation) && Objects.equals(doubleDiplome, that.doubleDiplome) && Objects.equals(debutAccreditation, that.debutAccreditation) && Objects.equals(finAccreditation, that.finAccreditation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, diplome, n0Annee, nomFormation, doubleDiplome, debutAccreditation, finAccreditation);
    }
}
