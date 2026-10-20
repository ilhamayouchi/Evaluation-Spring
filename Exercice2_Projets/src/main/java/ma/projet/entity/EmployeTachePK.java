package ma.projet.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeTachePK implements Serializable {

    private long employe;
    private long tache;

    public EmployeTachePK() {
    }

    public EmployeTachePK(long employe, long tache) {
        this.employe = employe;
        this.tache = tache;
    }

    public long getEmploye() {
        return employe;
    }

    public void setEmploye(long employe) {
        this.employe = employe;
    }

    public long getTache() {
        return tache;
    }

    public void setTache(long tache) {
        this.tache = tache;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EmployeTachePK)) return false;
        EmployeTachePK k = (EmployeTachePK) o;
        return employe == k.employe && tache == k.tache;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employe, tache);
    }
}
