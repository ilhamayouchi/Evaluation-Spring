package ma.projet.service;

import ma.projet.dao.EmployeDao;
import ma.projet.dao.EmployeTacheDao;
import ma.projet.dao.ProjetDao;
import ma.projet.entity.Employe;
import ma.projet.entity.Projet;
import ma.projet.entity.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeService {

    @Autowired
    private EmployeDao employeDao;

    @Autowired
    private EmployeTacheDao employeTacheDao;

    @Autowired
    private ProjetDao projetDao;

    @Transactional
    public void addEmploye(Employe employe) {
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        employeDao.save(employe);
    }

    @Transactional(readOnly = true)
    public List<Employe> getAllEmployes() {

        return employeDao.findAll();
    }

    // Tâches réalisées par un employé
    @Transactional(readOnly = true)
    public List<Tache> getTachesRealisees(Employe employe) {

        return employeTacheDao.findTachesByEmploye(employe);
    }


    @Transactional(readOnly = true)
    public List<Projet> getProjetsGeres(Employe employe) {

        return projetDao.findByChefProjet(employe);
    }
}
