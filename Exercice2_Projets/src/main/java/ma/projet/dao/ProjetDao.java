package ma.projet.dao;

import ma.projet.entity.Employe;
import ma.projet.entity.Projet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjetDao extends AbstractDao<Projet> {

    public ProjetDao() {
        super(Projet.class);
    }

    // Projets gérés par un employé "chef de projet"
    public List<Projet> findByChefProjet(Employe employe) {
        return session()
                .createQuery("from Projet p where p.chefProjet.id = :id", Projet.class)
                .setParameter("id", employe.getId())
                .list();
    }
}
