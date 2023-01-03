package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Admin;
import gr.ds.restapi.entity.Privilege;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PrivilegeDAO implements EntityDAO<Privilege>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Privilege> showALl() {
        return null;
    }

    @Override
    public int addEntity(Privilege entity) {
       Session session = entityManager.unwrap(Session.class);
       session.save(entity);

       session.close();
       return 0;
    }

    @Override
    public int deleteEntityByUsername(int id) {
        return 0;
    }

    @Override
    public int updateEntity(Privilege entity) {
        return 0;
    }

    @Override
    public Privilege getEntity(String name) {
        Session session = entityManager.unwrap(Session.class);

        try {
            Query privilegeQ = session.createQuery("select p from Privilege p where p.name= :name", Privilege.class).setParameter("name", name);
            Privilege privilege = (Privilege) privilegeQ.getSingleResult();
            return privilege;
        }catch (NoResultException e){
            return null;
        }
    }
}
