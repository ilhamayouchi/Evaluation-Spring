package ma.projet.dao;

import ma.projet.entity.Femme;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public class FemmeDao extends AbstractDao<Femme> {

    public FemmeDao() {
        super(Femme.class);
    }

    // Femme la plus âgée : la requête trie en base au lieu de charger toutes les femmes
    public Femme findPlusAgee() {
        return session()
                .createQuery("from Femme f order by f.dateNaissance asc", Femme.class)
                .setMaxResults(1)
                .uniqueResult();
    }

    // Nombre d'enfants d'une femme entre deux dates (requête native nommée)
    public int nbEnfantsEntreDates(Femme femme, Date d1, Date d2) {
        Number n = (Number) session().getNamedQuery("Femme.nbEnfantsEntreDates")
                .setParameter("id", femme.getId())
                .setParameter("d1", d1, TemporalType.DATE)
                .setParameter("d2", d2, TemporalType.DATE)
                .uniqueResult();
        return n == null ? 0 : n.intValue();
    }

    // Femmes mariées au moins deux fois (requête nommée)
    public List<Femme> findMarieesDeuxFoisOuPlus() {
        return session().createNamedQuery("Femme.mariees2Fois", Femme.class).list();
    }
}
