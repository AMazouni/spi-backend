package fr.ubo.spibackend.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PromotionPK implements Serializable {
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    @Id
    private String codeFormation;
    @Column(name = "ANNEE_UNIVERSITAIRE", nullable = false, length = 10)
    @Id
    private String anneeUniversitaire;

    public PromotionPK(String codeFormation, String anneeUniversitaire) {
        this.codeFormation = codeFormation;
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public PromotionPK() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionPK that = (PromotionPK) o;
        return Objects.equals(codeFormation, that.codeFormation) && Objects.equals(anneeUniversitaire, that.anneeUniversitaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, anneeUniversitaire);
    }
}
