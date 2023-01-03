package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Citizen;
import gr.ds.restapi.entity.Vet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class VetDAOImpl implements EntityDAO<Vet> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Vet> showALl() {
        Session session = entityManager.unwrap(Session.class);

        Query<Vet> vetQuery = session.createQuery("SELECT v FROM Vet v", Vet.class);

        session.close();

        List<Vet> vets = vetQuery.getResultList();

        return vets;
    }

    @Override
    @Transactional
    public int addEntity(Vet vet) {
        Session session = entityManager.unwrap(Session.class);

        session.save(vet);
        session.close();

        return 0;
    }

    @Override
    public int deleteEntityByUsername(int id) {
        return 0;
    }

    @Override
    public int updateEntity(Vet user) {
        return 0;
    }

    @Override
    public Vet getEntity(String username) {

        Session session = entityManager.unwrap(Session.class);

        Vet tmp = session.createQuery("SELECT v FROM Vet v WHERE v.username = :username", Vet.class).setParameter("username", username).getSingleResult();

        return tmp;
    }
}
