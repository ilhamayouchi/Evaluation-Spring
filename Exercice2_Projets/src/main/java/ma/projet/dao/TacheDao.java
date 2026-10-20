package ma.projet.dao;

import ma.projet.entity.Projet;
import ma.projet.entity.Tache;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TacheDao extends AbstractDao<Tache> {

    public TacheDao() {
        super(Tache.class);
    }

    // Tâches planifiées pour un projet
    public List<Tache> findByProjet(Projet projet) {
        return session()
                .createQuery("from Tache t where t.projet.id = :id", Tache.class)
                .setParameter("id", projet.getId())
                .list();
    }

    // Tâches dont le prix est supérieur à 1000 DH (requête nommée)
    public List<Tache> findPrixSup1000() {
        return session().createNamedQuery("Tache.prixSup1000", Tache.class).list();
    }
}
