package fr.ubo.spibackend.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ENSEIGNANT", schema = "DOSI", catalog = "")
public class EnseignantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_ENSEIGNANT", nullable = false, precision = 0)
    private short noEnseignant;
    @Basic
    @Column(name = "TYPE", nullable = false, length = 5)
    private String type;
    @Basic
    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;
    @Basic
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;
    @Basic
    @Column(name = "ADRESSE", nullable = false, length = 255)
    private String adresse;
    @Basic
    @Column(name = "CODE_POSTAL", nullable = false, length = 10)
    private String codePostal;
    @Basic
    @Column(name = "VILLE", nullable = false, length = 255)
    private String ville;
    @Basic
    @Column(name = "PAYS", nullable = false, length = 5)
    private String pays;
    @Basic
    @Column(name = "MOBILE", nullable = false, length = 20)
    private String mobile;
    @Basic
    @Column(name = "TELEPHONE", nullable = true, length = 20)
    private String telephone;
    @Basic
    @Column(name = "EMAIL_UBO", nullable = false, length = 255)
    private String emailUbo;
    @Basic
    @Column(name = "EMAIL_PERSO", nullable = true, length = 255)
    private String emailPerso;

    public short getNoEnseignant() {
        return noEnseignant;
    }

    public void setNoEnseignant(short noEnseignant) {
        this.noEnseignant = noEnseignant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailUbo() {
        return emailUbo;
    }

    public void setEmailUbo(String emailUbo) {
        this.emailUbo = emailUbo;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnseignantEntity that = (EnseignantEntity) o;
        return noEnseignant == that.noEnseignant && Objects.equals(type, that.type) && Objects.equals(sexe, that.sexe) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(adresse, that.adresse) && Objects.equals(codePostal, that.codePostal) && Objects.equals(ville, that.ville) && Objects.equals(pays, that.pays) && Objects.equals(mobile, that.mobile) && Objects.equals(telephone, that.telephone) && Objects.equals(emailUbo, that.emailUbo) && Objects.equals(emailPerso, that.emailPerso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noEnseignant, type, sexe, nom, prenom, adresse, codePostal, ville, pays, mobile, telephone, emailUbo, emailPerso);
    }
}
