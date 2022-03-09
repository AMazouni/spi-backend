package fr.ubo.spibackend.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "UNITE_ENSEIGNEMENT", schema = "DOSI", catalog = "")
@IdClass(UniteEnseignementEntityPK.class)
public class UniteEnseignementEntity {
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
    private short noEnseignant;
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
    private BigInteger nbhCm;
    @Basic
    @Column(name = "NBH_TD", nullable = true, precision = 0)
    private Byte nbhTd;
    @Basic
    @Column(name = "NBH_TP", nullable = true, precision = 0)
    private Byte nbhTp;

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

    public short getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(short noEnseignant) {
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

    public BigInteger getNbhCm() {
        return nbhCm;
    }

    public void setNbhCm(BigInteger nbhCm) {
        this.nbhCm = nbhCm;
    }

    public Byte getNbhTd() {
        return nbhTd;
    }

    public void setNbhTd(Byte nbhTd) {
        this.nbhTd = nbhTd;
    }

    public Byte getNbhTp() {
        return nbhTp;
    }

    public void setNbhTp(Byte nbhTp) {
        this.nbhTp = nbhTp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniteEnseignementEntity that = (UniteEnseignementEntity) o;
        return noEnseignant == that.noEnseignant && Objects.equals(codeFormation, that.codeFormation) && Objects.equals(codeUe, that.codeUe) && Objects.equals(designation, that.designation) && Objects.equals(semestre, that.semestre) && Objects.equals(description, that.description) && Objects.equals(nbhCm, that.nbhCm) && Objects.equals(nbhTd, that.nbhTd) && Objects.equals(nbhTp, that.nbhTp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, codeUe, noEnseignant, designation, semestre, description, nbhCm, nbhTd, nbhTp);
    }
}
