package ma.projet.service;

import ma.projet.dao.CategorieDao;
import ma.projet.entity.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieDao categorieDao;

    @Transactional
    public void addCategorie(Categorie categorie) {
        if (categorie.getCode() == null || categorie.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Le code de la catégorie est obligatoire");
        }
        categorieDao.save(categorie);
    }

    @Transactional(readOnly = true)
    public List<Categorie> getAllCategories() {
        return categorieDao.findAll();
    }
}
