package ma.projet.service;

import ma.projet.dao.HommeDao;
import ma.projet.entity.Femme;
import ma.projet.entity.Homme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HommeService {

    @Autowired
    private HommeDao hommeDao;

    @Transactional
    public void addHomme(Homme homme) {
        if (homme.getNom() == null || homme.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        hommeDao.save(homme);
    }

    @Transactional(readOnly = true)
    public List<Homme> getAllHommes() {

        return hommeDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Femme> getEpousesEntreDates(Homme homme, Date d1, Date d2) {
        return hommeDao.findEpousesEntreDates(homme, d1, d2);
    }
}
