package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Citizen;
import gr.ds.restapi.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements  EntityDAO<User> {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CitizenDAOImpl citizenDAO;

    @Override
    public List<User> showALl() {
        return null;
    }

    @Override
    public int addEntity(User entity) {
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
    @Transactional
    public int updateEntity(User user) {
        Session session = entityManager.unwrap(Session.class);

        int id = user.getId();

        User oldUser = session.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class).setParameter("id", id).getSingleResult();

        session.evict(oldUser);

        session.update(user);

        return 0;
    }

    @Override
    public User getEntity(String username) {
        return null;
    }
}
