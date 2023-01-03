package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Admin;
import gr.ds.restapi.entity.Citizen;
import gr.ds.restapi.entity.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDAO implements EntityDAO<Role>{


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> showALl() {
        return null;
    }

    @Override
    public int addEntity(Role entity) {
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
    public int updateEntity(Role entity) {

        return 0;
    }

    @Override
    public Role getEntity(String name) {
        Session session = entityManager.unwrap(Session.class);

        try {

            Query roleQ = session.createQuery("select r from Role r where r.name=:name", Role.class).setParameter("name", name);
            Role role = (Role) roleQ.getSingleResult();
            return role;
        }catch (NoResultException e){
            return null;
        }
    }
}
