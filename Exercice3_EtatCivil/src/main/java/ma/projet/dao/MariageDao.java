package ma.projet.dao;

import ma.projet.entity.Homme;
import ma.projet.entity.Mariage;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class MariageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Mariage mariage) {
        sessionFactory.getCurrentSession().save(mariage);
    }

    public List<Mariage> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Mariage", Mariage.class).list();
    }

    public List<Mariage> findByHomme(Homme homme) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Mariage m where m.homme.id = :id order by m.pK.dateDebut", Mariage.class)
                .setParameter("id", homme.getId())
                .list();
    }


    @SuppressWarnings("unchecked")
    public int countHommesMariesQuatreFemmes(Date d1, Date d2) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mariage.class);
        criteria.add(Restrictions.between("pK.dateDebut", d1, d2));
        criteria.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("pK.homme"))
                .add(Projections.rowCount()));
        List<Object[]> lignes = criteria.list();
        int nb = 0;
        for (Object[] ligne : lignes) {
            if (((Number) ligne[1]).intValue() == 4) {
                nb++;
            }
        }
        return nb;
    }
}
