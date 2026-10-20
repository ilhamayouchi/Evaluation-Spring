package ma.projet.service;

import ma.projet.dao.FemmeDao;
import ma.projet.entity.Femme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FemmeService {

    @Autowired
    private FemmeDao femmeDao;

    @Transactional
    public void addFemme(Femme femme) {
        if (femme.getNom() == null || femme.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        femmeDao.save(femme);
    }

    @Transactional(readOnly = true)
    public List<Femme> getAllFemmes() {

        return femmeDao.findAll();
    }

    @Transactional(readOnly = true)
    public Femme getFemmeLaPlusAgee() {

        return femmeDao.findPlusAgee();
    }

    @Transactional(readOnly = true)
    public int getNbEnfantsEntreDates(Femme femme, Date d1, Date d2) {
        return femmeDao.nbEnfantsEntreDates(femme, d1, d2);
    }

    @Transactional(readOnly = true)
    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {

        return femmeDao.findMarieesDeuxFoisOuPlus();
    }
}
