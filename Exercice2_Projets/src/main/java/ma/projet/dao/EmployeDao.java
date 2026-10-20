package ma.projet.dao;

import ma.projet.entity.Employe;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeDao extends AbstractDao<Employe> {

    public EmployeDao() {
        super(Employe.class);
    }
}
