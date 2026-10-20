package ma.projet.service;

import ma.projet.dao.EmployeTacheDao;
import ma.projet.dao.TacheDao;
import ma.projet.entity.EmployeTache;
import ma.projet.entity.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TacheService {

    @Autowired
    private TacheDao tacheDao;

    @Autowired
    private EmployeTacheDao employeTacheDao;

    @Transactional
    public void addTache(Tache tache) {
        if (tache.getPrix() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        tacheDao.save(tache);
    }

    // Affectation d'une tâche à un employé avec les dates réelles
    @Transactional
    public void addEmployeTache(EmployeTache employeTache) {
        if (employeTache.getDateFinReelle() != null && employeTache.getDateDebutReelle() != null
                && employeTache.getDateFinReelle().before(employeTache.getDateDebutReelle())) {
            throw new IllegalArgumentException("La date de fin réelle doit être postérieure à la date de début réelle");
        }
        employeTacheDao.save(employeTache);
    }

    @Transactional(readOnly = true)
    public List<Tache> getAllTaches() {
        return tacheDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Tache> getTachesPrixSup1000() {
        return tacheDao.findPrixSup1000();
    }

    @Transactional(readOnly = true)
    public List<EmployeTache> getTachesRealiseesEntreDates(Date d1, Date d2) {
        return employeTacheDao.findEntreDates(d1, d2);
    }
}
