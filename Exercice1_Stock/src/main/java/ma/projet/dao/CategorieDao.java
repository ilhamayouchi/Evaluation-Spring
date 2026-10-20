package ma.projet.dao;

import ma.projet.entity.Categorie;
import org.springframework.stereotype.Repository;

@Repository
public class CategorieDao extends AbstractDao<Categorie> {

    public CategorieDao() {
        super(Categorie.class);
    }
}
