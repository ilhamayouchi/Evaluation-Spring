package ma.projet.dao;

import ma.projet.entity.Femme;
import ma.projet.entity.Homme;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class HommeDao extends AbstractDao<Homme> {

    public HommeDao() {
        super(Homme.class);
    }

    public List<Femme> findEpousesEntreDates(Homme homme, Date d1, Date d2) {
        return session()
                .createQuery("select m.femme from Mariage m "
                        + "where m.homme.id = :id and m.pK.dateDebut between :d1 and :d2", Femme.class)
                .setParameter("id", homme.getId()).setParameter("d1", d1).setParameter("d2", d2)
                .list();
    }
}
