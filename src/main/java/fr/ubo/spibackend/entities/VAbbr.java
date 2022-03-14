package fr.ubo.spibackend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class VAbbr {
    @Id
    private String code;

    private String abreviation;
    private String signification;

    public VAbbr() {
    }

    public VAbbr(String code, String abreviation, String signification) {
        this.code = code;
        this.abreviation = abreviation;
        this.signification = signification;
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
        return "VAbbr{" +
                "code='" + code + '\'' +
                ", abreviation='" + abreviation + '\'' +
                ", signification='" + signification + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VAbbr that = (VAbbr) o;
        return Objects.equals(code, that.code) && Objects.equals(abreviation, that.abreviation) && Objects.equals(signification, that.signification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, abreviation, signification);
    }
}
