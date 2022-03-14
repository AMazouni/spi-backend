package fr.ubo.spibackend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DomaineViews {
     @Id
    private String code;

    private String abreviation;
    private String signification;

    public DomaineViews() {
    }

    public DomaineViews(String code, String abreviation, String signification) {
        this.code = code;
        this.abreviation = abreviation;
        this.signification = signification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getSignification() {
        return signification;
    }

    public void setSignification(String signification) {
        this.signification = signification;
    }

    @Override
    public String toString() {
        return "DomaineViews{" +
                "code='" + code + '\'' +
                ", abreviation='" + abreviation + '\'' +
                ", signification='" + signification + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomaineViews that = (DomaineViews) o;
        return Objects.equals(code, that.code) && Objects.equals(abreviation, that.abreviation) && Objects.equals(signification, that.signification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, abreviation, signification);
    }
}
