package ma.projet.service;

import ma.projet.dao.EmployeTacheDao;
import ma.projet.dao.ProjetDao;
import ma.projet.dao.TacheDao;
import ma.projet.entity.EmployeTache;
import ma.projet.entity.Projet;
import ma.projet.entity.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjetService {

    @Autowired
    private ProjetDao projetDao;

    @Autowired
    private TacheDao tacheDao;

    @Autowired
    private EmployeTacheDao employeTacheDao;

    @Transactional
    public void addProjet(Projet projet) {
        if (projet.getDateFin() != null && projet.getDateDebut() != null
                && projet.getDateFin().before(projet.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début");
        }
        projetDao.save(projet);
    }

    @Transactional(readOnly = true)
    public List<Projet> getAllProjets() {

        return projetDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Tache> getTachesPlanifiees(Projet projet) {

        return tacheDao.findByProjet(projet);
    }


    @Transactional(readOnly = true)
    public List<EmployeTache> getTachesRealisees(Projet projet) {

        return employeTacheDao.findByProjet(projet);
    }
}
