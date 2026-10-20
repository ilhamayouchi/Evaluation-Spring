package ma.projet.service;

import ma.projet.dao.MariageDao;
import ma.projet.entity.Homme;
import ma.projet.entity.Mariage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MariageService {

    @Autowired
    private MariageDao mariageDao;

    @Transactional
    public void addMariage(Mariage mariage) {
        if (mariage.getNbrEnfant() < 0) {
            throw new IllegalArgumentException("Le nombre d'enfants ne peut pas être négatif");
        }
        if (mariage.getDateFin() != null && mariage.getDateFin().before(mariage.getpK().getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début");
        }
        mariageDao.save(mariage);
    }

    @Transactional(readOnly = true)
    public List<Mariage> getMariagesDeHomme(Homme homme) {

        return mariageDao.findByHomme(homme);
    }

    @Transactional(readOnly = true)
    public int getNbHommesMariesQuatreFemmes(Date d1, Date d2) {
        return mariageDao.countHommesMariesQuatreFemmes(d1, d2);
    }
}
