package ma.projet.service;

import ma.projet.dao.CommandeDao;
import ma.projet.dao.LigneCommandeDao;
import ma.projet.entity.Commande;
import ma.projet.entity.LigneCommandeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeDao commandeDao;

    @Autowired
    private LigneCommandeDao ligneCommandeDao;

    @Transactional
    public void addCommande(Commande commande) {
        if (commande.getDate() == null) {
            throw new IllegalArgumentException("La date de la commande est obligatoire");
        }
        commandeDao.save(commande);
    }

    @Transactional
    public void addLigne(LigneCommandeProduit ligne) {
        if (ligne.getQuantite() <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        ligneCommandeDao.save(ligne);
    }

    @Transactional(readOnly = true)
    public List<LigneCommandeProduit> getLignesDeLaCommande(Commande commande) {
        return ligneCommandeDao.findByCommande(commande);
    }
}
