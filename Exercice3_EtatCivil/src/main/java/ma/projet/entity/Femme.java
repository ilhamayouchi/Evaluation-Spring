package ma.projet.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

@Entity
@NamedNativeQuery(name = "Femme.nbEnfantsEntreDates",
        query = "select coalesce(sum(nbrEnfant), 0) from Mariage where femme = :id and dateDebut between :d1 and :d2")
@NamedQuery(name = "Femme.mariees2Fois",
        query = "from Femme f where (select count(m) from Mariage m where m.femme = f) >= 2")
public class Femme extends Personne {

    public Femme() {
    }

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
}
