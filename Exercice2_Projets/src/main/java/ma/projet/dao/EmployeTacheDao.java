package ma.projet.dao;

import ma.projet.entity.Employe;
import ma.projet.entity.EmployeTache;
import ma.projet.entity.Projet;
import ma.projet.entity.Tache;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;



@Repository
public class EmployeTacheDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(EmployeTache employeTache) {

        sessionFactory.getCurrentSession().save(employeTache);
    }

    // Tâches réalisées par un employé
    public List<Tache> findTachesByEmploye(Employe employe) {
        return sessionFactory.getCurrentSession()
                .createQuery("select et.tache from EmployeTache et where et.employe.id = :id", Tache.class)
                .setParameter("id", employe.getId())
                .list();
    }

    // Tâches réalisées d'un projet (avec les dates réelles)
    public List<EmployeTache> findByProjet(Projet projet) {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache et where et.tache.projet.id = :id", EmployeTache.class)
                .setParameter("id", projet.getId())
                .list();
    }

    // Tâches réalisées entre deux dates
    public List<EmployeTache> findEntreDates(Date d1, Date d2) {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache et where et.dateDebutReelle >= :d1 and et.dateFinReelle <= :d2",
                        EmployeTache.class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
    }
}
