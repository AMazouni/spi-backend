package fr.ubo.spibackend.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "UNITE_ENSEIGNEMENT", schema = "DOSI", catalog = "")
@IdClass(UniteEnseignementPK.class)
public class UniteEnseignement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_UE", nullable = false, length = 8)
    private String codeUe;
    @Basic
    @Column(name = "NO_ENSEIGNANT", nullable = false, precision = 0)
    private Short noEnseignant;
    @Basic
    @Column(name = "DESIGNATION", nullable = false, length = 64)
    private String designation;
    @Basic
    @Column(name = "SEMESTRE", nullable = false, length = 3)
    private String semestre;
    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 256)
    private String description;
    @Basic

    @Column(name = "NBH_CM", nullable = true, precision = 0)
    private int nbhCm;
    @Basic
    @Column(name = "NBH_TD", nullable = true, precision = 0)
    private int nbhTd;
    @Basic
    @Column(name = "NBH_TP", nullable = true, precision = 0)
    private int nbhTp;


    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public String getCodeUe() {
        return codeUe;
    }

    public void setCodeUe(String codeUe) {
        this.codeUe = codeUe;
    }

    public Short getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Short noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbhCm() {
        return nbhCm;
    }

    public void setNbhCm(int nbhCm) {
        this.nbhCm = nbhCm;
    }

    public int getNbhTd() {
        return nbhTd;
    }

    public void setNbhTd(int nbhTd) {
        this.nbhTd = nbhTd;
    }

    public int getNbhTp() {
        return nbhTp;
    }

    public void setNbhTp(int nbhTp) {
        this.nbhTp = nbhTp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniteEnseignement that = (UniteEnseignement) o;
        return Objects.equals(codeFormation, that.codeFormation) && Objects.equals(codeUe, that.codeUe) && Objects.equals(noEnseignant, that.noEnseignant) && Objects.equals(designation, that.designation) && Objects.equals(semestre, that.semestre) && Objects.equals(description, that.description) && Objects.equals(nbhCm, that.nbhCm) && Objects.equals(nbhTd, that.nbhTd) && Objects.equals(nbhTp, that.nbhTp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, codeUe, noEnseignant, designation, semestre, description, nbhCm, nbhTd, nbhTp);
    }

    @ManyToOne
    @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", insertable = false, updatable = false)
    private Formation formationByCodeFormation;

    public Formation getFormationByCodeFormation() {
        return formationByCodeFormation;
    }

    public void setFormationByCodeFormation(Formation manyToOne) {
        this.formationByCodeFormation = manyToOne;
    }
}
