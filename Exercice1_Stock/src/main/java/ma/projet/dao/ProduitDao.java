package ma.projet.dao;

import ma.projet.entity.Categorie;
import ma.projet.entity.Produit;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProduitDao extends AbstractDao<Produit> {

    public ProduitDao() {
        super(Produit.class);
    }

    // Produits d'une catégorie
    public List<Produit> findByCategorie(Categorie categorie) {
        return session()
                .createQuery("from Produit p where p.categorie.id = :id", Produit.class)
                .setParameter("id", categorie.getId())
                .list();
    }

    // Produits commandés entre deux dates
    public List<Produit> findCommandesEntreDates(Date d1, Date d2) {
        return session()
                .createQuery("select distinct l.produit from LigneCommandeProduit l "
                        + "where l.commande.date between :d1 and :d2", Produit.class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
    }

    // Produits dont le prix est supérieur à 100 DH (requête nommée)
    public List<Produit> findPrixSup100() {
        return session().createNamedQuery("Produit.prixSup100", Produit.class).list();
    }
}
