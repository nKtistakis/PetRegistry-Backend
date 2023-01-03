package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Admin;
import gr.ds.restapi.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AdminDAOImpl implements EntityDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List showALl() {
        return null;
    }

    @Override
    @Transactional
    public int addEntity(Object user) {

        Session session = entityManager.unwrap(Session.class);

        session.save(user);
        session.close();
        return 0;
    }

    @Override
    public int deleteEntityByUsername(int id) {
        return 0;
    }

    @Override
    public int updateEntity(Object user) {
        return 0;
    }

    @Override
    public Admin getEntity(String username) {
        Session session = entityManager.unwrap(Session.class);

        try {
            Query adminQ = session.createQuery("select a from Admin a where a.username= :username", Admin.class).setParameter("username", username);
            Admin admin = (Admin) adminQ.getSingleResult();
            return admin;
        }catch (NoResultException e){
            return null;
        }



    }
}
