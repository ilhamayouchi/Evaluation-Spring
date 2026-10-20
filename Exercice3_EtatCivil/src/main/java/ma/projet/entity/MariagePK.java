package ma.projet.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class MariagePK implements Serializable {

    private long homme;
    private long femme;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    public MariagePK() {
    }

    public MariagePK(long homme, long femme, Date dateDebut) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
    }

    public long getHomme() {
        return homme;
    }

    public void setHomme(long homme) {
        this.homme = homme;
    }

    public long getFemme() {
        return femme;
    }

    public void setFemme(long femme) {
        this.femme = femme;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MariagePK)) return false;
        MariagePK k = (MariagePK) o;
        return homme == k.homme && femme == k.femme && Objects.equals(dateDebut, k.dateDebut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homme, femme, dateDebut);
    }
}
