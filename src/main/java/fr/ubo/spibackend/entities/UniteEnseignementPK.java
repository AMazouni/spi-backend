package fr.ubo.spibackend.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UniteEnseignementPK implements Serializable {
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    @Id

    private String codeFormation;
    @Column(name = "CODE_UE", nullable = false, length = 8)
    @Id
    private String codeUe;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniteEnseignementPK that = (UniteEnseignementPK) o;
        return Objects.equals(codeFormation, that.codeFormation) && Objects.equals(codeUe, that.codeUe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeFormation, codeUe);
    }
}
