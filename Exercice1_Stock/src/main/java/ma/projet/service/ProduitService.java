package ma.projet.service;

import ma.projet.dao.ProduitDao;
import ma.projet.entity.Categorie;
import ma.projet.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitDao produitDao;

    @Transactional
    public void addProduit(Produit produit) {
        if (produit.getPrix() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        produitDao.save(produit);
    }

    @Transactional(readOnly = true)
    public List<Produit> getAllProduits() {
        return produitDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitsParCategorie(Categorie categorie) {
        return produitDao.findByCategorie(categorie);
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitsCommandesEntreDates(Date d1, Date d2) {
        return produitDao.findCommandesEntreDates(d1, d2);
    }

    @Transactional(readOnly = true)
    public List<Produit> getProduitsPrixSup100() {
        return produitDao.findPrixSup100();
    }
}
