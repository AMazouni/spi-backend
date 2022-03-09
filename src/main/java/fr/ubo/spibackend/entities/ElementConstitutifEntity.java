package fr.ubo.spibackend.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ELEMENT_CONSTITUTIF", schema = "DOSI", catalog = "")
@IdClass(ElementConstitutifEntityPK.class)
public class ElementConstitutifEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_UE", nullable = false, length = 8)
    private String codeUe;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_EC", nullable = false, length = 8)
    private String codeEc;
    @Basic
    @Column(name = "NO_ENSEIGNANT", nullable = false, precision = 0)
    private short noEnseignant;
    @Basic
    @Column(name = "DESIGNATION", nullable = false, length = 64)
    private String designation;
    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 240)
    private String description;
    @Basic
    @Column(name = "NBH_CM", nullable = true, precision = 0)
    private Byte nbhCm;
    @Basic
    @Column(name = "NBH_TD", nullable = true, precision = 0)
    private Byte nbhTd;
    @Basic
    @Column(name = "NBH_TP", nullable = true, precision = 0)
    private Byte nbhTp;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false), @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE", nullable = false)})
    private UniteEnseignementEntity uniteEnseignement;
    @ManyToOne
    @JoinColumn(name = "NO_ENSEIGNANT", referencedColumnName = "NO_ENSEIGNANT", nullable = false)
    private EnseignantEntity enseignantByNoEnseignant;

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

    public String getCodeEc() {
        return codeEc;
    }

    public void setCodeEc(String codeEc) {
        this.codeEc = codeEc;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getNbhCm() {
        return nbhCm;
    }

    public void setNbhCm(Byte nbhCm) {
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
        ElementConstitutifEntity that = (ElementConstitutifEntity) o;
        return noEnseignant == that.noEnseignant && Objects.equals(codeFormation, that.codeFormation) && Objects.equals(codeUe, that.codeUe) && Objects.equals(codeEc, that.codeEc) && Objects.equals(designation, that.designation) && Objects.equals(description, that.description) && Objects.equals(nbhCm, that.nbhCm) && Objects.equals(nbhTd, that.nbhTd) && Objects.equals(nbhTp, that.nbhTp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, codeUe, codeEc, noEnseignant, designation, description, nbhCm, nbhTd, nbhTp);
    }

    public UniteEnseignementEntity getUniteEnseignement() {
        return uniteEnseignement;
    }

    public void setUniteEnseignement(UniteEnseignementEntity uniteEnseignement) {
        this.uniteEnseignement = uniteEnseignement;
    }

    public EnseignantEntity getEnseignantByNoEnseignant() {
        return enseignantByNoEnseignant;
    }

    public void setEnseignantByNoEnseignant(EnseignantEntity enseignantByNoEnseignant) {
        this.enseignantByNoEnseignant = enseignantByNoEnseignant;
    }
}
