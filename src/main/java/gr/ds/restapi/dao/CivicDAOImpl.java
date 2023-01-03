package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Citizen;
import gr.ds.restapi.entity.CivicOfficial;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CivicDAOImpl implements EntityDAO<CivicOfficial> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CivicOfficial> showALl() {
        Session session = entityManager.unwrap(Session.class);

        Query<CivicOfficial> civicOfficialQuery = session.createQuery("SELECT c FROM CivicOfficial c", CivicOfficial.class);

        session.close();

        List<CivicOfficial> civicOfficials = civicOfficialQuery.getResultList();

        return civicOfficials;
    }

    @Override
    @Transactional
    public int addEntity(CivicOfficial user) {
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
    public int updateEntity(CivicOfficial user) {
        return 0;
    }

    @Override
    @Transactional
    public CivicOfficial getEntity(String username) {

        Session session = entityManager.unwrap(Session.class);

        CivicOfficial tmp = session.createQuery("SELECT c FROM CivicOfficial c WHERE c.username = :username", CivicOfficial.class).setParameter("username", username).getSingleResult();
        CivicOfficial civic = new CivicOfficial(tmp.getFullName(), tmp.getRegion());

        return tmp;
    }
}
