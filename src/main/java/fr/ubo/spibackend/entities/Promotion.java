package fr.ubo.spibackend.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PROMOTION", schema = "DOSI", catalog = "")
@IdClass(PromotionPK.class)
public class Promotion implements Serializable {
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;
    @Id
    @Column(name = "ANNEE_UNIVERSITAIRE", nullable = false, length = 10)
    private String anneeUniversitaire;
    @Basic
    @Column(name = "NO_ENSEIGNANT", nullable = true, precision = 0)
    private Short noEnseignant;
    @Basic
    @Column(name = "SIGLE_PROMOTION", nullable = true, length = 16)
    private String siglePromotion;
    @Basic
    @Column(name = "NB_MAX_ETUDIANT", nullable = false, precision = 0)
    private byte nbMaxEtudiant;
    @Basic
    @Column(name = "DATE_REPONSE_LP", nullable = true)
    private Date dateReponseLp;
    @Basic
    @Column(name = "DATE_REPONSE_LALP", nullable = true)
    private Date dateReponseLalp;
    @Basic
    @Column(name = "DATE_RENTREE", nullable = true)
    private Date dateRentree;
    @Basic
    @Column(name = "LIEU_RENTREE", nullable = true, length = 12)
    private String lieuRentree;
    @Basic
    @Column(name = "PROCESSUS_STAGE", nullable = true, length = 5)
    private String processusStage;
    @Basic
    @Column(name = "COMMENTAIRE", nullable = true, length = 255)
    private String commentaire;
    @OneToMany(mappedBy = "promotion")
    private List<Candidat> candidats;
    @OneToMany(mappedBy = "promotionEtudiant")
    private List<Etudiant> etudiants;
    @ManyToOne
    @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false,insertable = false,updatable = false)
    private Formation formationByCodeFormation;
    @ManyToOne
    @JoinColumn(name = "NO_ENSEIGNANT", referencedColumnName = "NO_ENSEIGNANT",insertable = false,updatable = false)
    private Enseignant enseignantByNoEnseignant;

    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public Short getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(Short noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public String getSiglePromotion() {
        return siglePromotion;
    }

    public void setSiglePromotion(String siglePromotion) {
        this.siglePromotion = siglePromotion;
    }

    public byte getNbMaxEtudiant() {
        return nbMaxEtudiant;
    }

    public void setNbMaxEtudiant(byte nbMaxEtudiant) {
        this.nbMaxEtudiant = nbMaxEtudiant;
    }

    public Date getDateReponseLp() {
        return dateReponseLp;
    }

    public void setDateReponseLp(Date dateReponseLp) {
        this.dateReponseLp = dateReponseLp;
    }

    public Date getDateReponseLalp() {
        return dateReponseLalp;
    }

    public void setDateReponseLalp(Date dateReponseLalp) {
        this.dateReponseLalp = dateReponseLalp;
    }

    public Date getDateRentree() {
        return dateRentree;
    }

    public void setDateRentree(Date dateRentree) {
        this.dateRentree = dateRentree;
    }

    public String getLieuRentree() {
        return lieuRentree;
    }

    public void setLieuRentree(String lieuRentree) {
        this.lieuRentree = lieuRentree;
    }

    public String getProcessusStage() {
        return processusStage;
    }

    public void setProcessusStage(String processusStage) {
        this.processusStage = processusStage;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion that = (Promotion) o;
        return nbMaxEtudiant == that.nbMaxEtudiant && Objects.equals(codeFormation, that.codeFormation) && Objects.equals(anneeUniversitaire, that.anneeUniversitaire) && Objects.equals(noEnseignant, that.noEnseignant) && Objects.equals(siglePromotion, that.siglePromotion) && Objects.equals(dateReponseLp, that.dateReponseLp) && Objects.equals(dateReponseLalp, that.dateReponseLalp) && Objects.equals(dateRentree, that.dateRentree) && Objects.equals(lieuRentree, that.lieuRentree) && Objects.equals(processusStage, that.processusStage) && Objects.equals(commentaire, that.commentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, anneeUniversitaire, noEnseignant, siglePromotion, nbMaxEtudiant, dateReponseLp, dateReponseLalp, dateRentree, lieuRentree, processusStage, commentaire);
    }

    public List<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Formation getFormationByCodeFormation() {
        return formationByCodeFormation;
    }

    public void setFormationByCodeFormation(Formation formationByCodeFormation) {
        this.formationByCodeFormation = formationByCodeFormation;
    }

    public Enseignant getEnseignantByNoEnseignant() {
        return enseignantByNoEnseignant;
    }

    public void setEnseignantByNoEnseignant(Enseignant enseignantByNoEnseignant) {
        this.enseignantByNoEnseignant = enseignantByNoEnseignant;
    }
}
