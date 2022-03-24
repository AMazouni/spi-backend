package fr.ubo.spibackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Enseignant;
import fr.ubo.spibackend.entities.Etudiant;
import fr.ubo.spibackend.entities.Formation;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class PromotionDTO {

    private String codeFormation;
    private String anneeUniversitaire;
    private Short noEnseignant;
    private String siglePromotion;
    private int nbMaxEtudiant;
    @JsonFormat(pattern="dd/MM/YYY")
    private Date dateReponseLp;
    @JsonFormat(pattern="dd/MM/YYY")
    private Date dateReponseLalp;
    @JsonFormat(pattern="dd/MM/YYY")
    private Date dateRentree;
    private String lieuRentree;
    private String processusStage;
    private String commentaire;
    private List<Candidat> candidats;
    private List<Etudiant> etudiants;
    private Formation formationByCodeFormation;
    private Enseignant enseignantByNoEnseignant;
    private Double nombreHeureTD;
    private Double nombreHeureTP;
    private Double nombreHeureCM;
    private Double nombreHeureETD;

    public PromotionDTO(String codeFormation, String anneeUniversitaire, Short noEnseignant, String siglePromotion, int nbMaxEtudiant, Date dateReponseLp, Date dateReponseLalp, Date dateRentree, String lieuRentree, String processusStage, String commentaire, List<Candidat> candidats, List<Etudiant> etudiants, Formation formationByCodeFormation, Enseignant enseignantByNoEnseignant, Double nombreHeureTD, Double nombreHeureTP, Double nombreHeureCM, Double nombreHeureETD) {
        this.codeFormation = codeFormation;
        this.anneeUniversitaire = anneeUniversitaire;
        this.noEnseignant = noEnseignant;
        this.siglePromotion = siglePromotion;
        this.nbMaxEtudiant = nbMaxEtudiant;
        this.dateReponseLp = dateReponseLp;
        this.dateReponseLalp = dateReponseLalp;
        this.dateRentree = dateRentree;
        this.lieuRentree = lieuRentree;
        this.processusStage = processusStage;
        this.commentaire = commentaire;
        this.candidats = candidats;
        this.etudiants = etudiants;
        this.formationByCodeFormation = formationByCodeFormation;
        this.enseignantByNoEnseignant = enseignantByNoEnseignant;
        this.nombreHeureTD = nombreHeureTD;
        this.nombreHeureTP = nombreHeureTP;
        this.nombreHeureCM = nombreHeureCM;
        this.nombreHeureETD = nombreHeureETD;
    }

    public PromotionDTO(String codeFormation, String anneeUniversitaire, Short noEnseignant, String siglePromotion, int nbMaxEtudiant, Date dateReponseLp, Date dateReponseLalp, Date dateRentree, String lieuRentree, String processusStage, String commentaire, List<Candidat> candidats, List<Etudiant> etudiants, Formation formationByCodeFormation, Enseignant enseignantByNoEnseignant) {
        this.codeFormation = codeFormation;
        this.anneeUniversitaire = anneeUniversitaire;
        this.noEnseignant = noEnseignant;
        this.siglePromotion = siglePromotion;
        this.nbMaxEtudiant = nbMaxEtudiant;
        this.dateReponseLp = dateReponseLp;
        this.dateReponseLalp = dateReponseLalp;
        this.dateRentree = dateRentree;
        this.lieuRentree = lieuRentree;
        this.processusStage = processusStage;
        this.commentaire = commentaire;
        this.candidats = candidats;
        this.etudiants = etudiants;
        this.formationByCodeFormation = formationByCodeFormation;
        this.enseignantByNoEnseignant = enseignantByNoEnseignant;
    }

    public PromotionDTO() {
    }

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

    public int getNbMaxEtudiant() {
        return nbMaxEtudiant;
    }

    public void setNbMaxEtudiant(int nbMaxEtudiant) {
        this.nbMaxEtudiant = nbMaxEtudiant;
    }

    public Date getDateReponseLp() {
        return dateReponseLp;
    }

    public void setDateReponseLp(Date dateReponseLp) {
        this.dateReponseLp = dateReponseLp;
    }

    public Double getNombreHeureTD() {
        return nombreHeureTD;
    }

    public void setNombreHeureTD(Double nombreHeureTD) {
        this.nombreHeureTD = nombreHeureTD;
    }

    public Double getNombreHeureTP() {
        return nombreHeureTP;
    }

    public void setNombreHeureTP(Double nombreHeureTP) {
        this.nombreHeureTP = nombreHeureTP;
    }

    public Double getNombreHeureCM() {
        return nombreHeureCM;
    }

    public void setNombreHeureCM(Double nombreHeureCM) {
        this.nombreHeureCM = nombreHeureCM;
    }

    public Double getNombreHeureETD() {
        return nombreHeureETD;
    }

    public void setNombreHeureETD(Double nombreHeureETD) {
        this.nombreHeureETD = nombreHeureETD;
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
