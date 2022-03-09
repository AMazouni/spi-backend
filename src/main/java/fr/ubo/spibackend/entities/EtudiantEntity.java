package fr.ubo.spibackend.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ETUDIANT", schema = "DOSI", catalog = "")
public class EtudiantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_ETUDIANT", nullable = false, length = 50)
    private String noEtudiant;
    @Basic
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String codeFormation;
    @Basic
    @Column(name = "ANNEE_UNIVERSITAIRE", nullable = false, length = 10)
    private String anneeUniversitaire;
    @Basic
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;
    @Basic
    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;
    @Basic
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private Date dateNaissance;
    @Basic
    @Column(name = "LIEU_NAISSANCE", nullable = false, length = 255)
    private String lieuNaissance;
    @Basic
    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;
    @Basic
    @Column(name = "TELEPHONE", nullable = true, length = 20)
    private String telephone;
    @Basic
    @Column(name = "MOBILE", nullable = true, length = 20)
    private String mobile;
    @Basic
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "EMAIL_UBO", nullable = true, length = 255)
    private String emailUbo;
    @Basic
    @Column(name = "ADRESSE", nullable = false, length = 255)
    private String adresse;
    @Basic
    @Column(name = "CODE_POSTAL", nullable = true, length = 10)
    private String codePostal;
    @Basic
    @Column(name = "VILLE", nullable = false, length = 255)
    private String ville;
    @Basic
    @Column(name = "PAYS_ORIGINE", nullable = false, length = 5)
    private String paysOrigine;
    @Basic
    @Column(name = "UNIVERSITE_ORIGINE", nullable = false, length = 6)
    private String universiteOrigine;
    @Basic
    @Column(name = "GROUPE_TP", nullable = true, precision = 0)
    private BigInteger groupeTp;
    @Basic
    @Column(name = "GROUPE_ANGLAIS", nullable = true, precision = 0)
    private BigInteger groupeAnglais;

    public String getNoEtudiant() {
        return noEtudiant;
    }

    public void setNoEtudiant(String noEtudiant) {
        this.noEtudiant = noEtudiant;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailUbo() {
        return emailUbo;
    }

    public void setEmailUbo(String emailUbo) {
        this.emailUbo = emailUbo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }

    public String getUniversiteOrigine() {
        return universiteOrigine;
    }

    public void setUniversiteOrigine(String universiteOrigine) {
        this.universiteOrigine = universiteOrigine;
    }

    public BigInteger getGroupeTp() {
        return groupeTp;
    }

    public void setGroupeTp(BigInteger groupeTp) {
        this.groupeTp = groupeTp;
    }

    public BigInteger getGroupeAnglais() {
        return groupeAnglais;
    }

    public void setGroupeAnglais(BigInteger groupeAnglais) {
        this.groupeAnglais = groupeAnglais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtudiantEntity that = (EtudiantEntity) o;
        return Objects.equals(noEtudiant, that.noEtudiant) && Objects.equals(codeFormation, that.codeFormation) && Objects.equals(anneeUniversitaire, that.anneeUniversitaire) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(sexe, that.sexe) && Objects.equals(dateNaissance, that.dateNaissance) && Objects.equals(lieuNaissance, that.lieuNaissance) && Objects.equals(nationalite, that.nationalite) && Objects.equals(telephone, that.telephone) && Objects.equals(mobile, that.mobile) && Objects.equals(email, that.email) && Objects.equals(emailUbo, that.emailUbo) && Objects.equals(adresse, that.adresse) && Objects.equals(codePostal, that.codePostal) && Objects.equals(ville, that.ville) && Objects.equals(paysOrigine, that.paysOrigine) && Objects.equals(universiteOrigine, that.universiteOrigine) && Objects.equals(groupeTp, that.groupeTp) && Objects.equals(groupeAnglais, that.groupeAnglais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noEtudiant, codeFormation, anneeUniversitaire, nom, prenom, sexe, dateNaissance, lieuNaissance, nationalite, telephone, mobile, email, emailUbo, adresse, codePostal, ville, paysOrigine, universiteOrigine, groupeTp, groupeAnglais);
    }
}
