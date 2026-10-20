package ma.projet.dao;

import ma.projet.entity.Commande;
import org.springframework.stereotype.Repository;

@Repository
public class CommandeDao extends AbstractDao<Commande> {

    public CommandeDao() {
        super(Commande.class);
    }
}
