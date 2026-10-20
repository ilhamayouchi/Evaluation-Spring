package ma.projet.dao;

import ma.projet.entity.Commande;
import ma.projet.entity.LigneCommandeProduit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LigneCommandeDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(LigneCommandeProduit ligne) {

        sessionFactory.getCurrentSession().save(ligne);
    }

    public List<LigneCommandeProduit> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from LigneCommandeProduit", LigneCommandeProduit.class).list();
    }

    // Lignes d'une commande
    public List<LigneCommandeProduit> findByCommande(Commande commande) {
        return sessionFactory.getCurrentSession()
                .createQuery("from LigneCommandeProduit l where l.commande.id = :id", LigneCommandeProduit.class)
                .setParameter("id", commande.getId())
                .list();
    }
}
