package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Citizen;

import org.hibernate.Session;
import org.hibernate.query.Query;



import org.springframework.stereotype.Repository;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class CitizenDAOImpl implements EntityDAO<Citizen> {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    @Transactional
    public List<Citizen> showALl() {
        Session session = entityManager.unwrap(Session.class);

        Query<Citizen> citizenQuery = session.createQuery("SELECT c FROM Citizen c", Citizen.class);

        session.close();

        List<Citizen> citizens = citizenQuery.getResultList();

        return citizens;
    }

    @Override
    @Transactional
    public int addEntity(Citizen citizen) {

        Session session = entityManager.unwrap(Session.class);

        session.save(citizen);
        session.close();

        return 0;
    }

    @Override
    @Transactional
    public int deleteEntityByUsername(int id) {

        Session session = entityManager.unwrap(Session.class);

        Citizen citizen = session.createQuery("SELECT c FROM Citizen c WHERE c.id = :id", Citizen.class).setParameter("id", id).getSingleResult();

        session.remove(citizen);
        return 0;
    }

    @Override
    @Transactional
    public int updateEntity(Citizen citizen) {

        Session session = entityManager.unwrap(Session.class);

        int id = citizen.getId();
        System.out.println(citizen.getCode());
        Citizen oldCitizen = session.createQuery("SELECT c FROM Citizen c WHERE c.id = :id", Citizen.class).setParameter("id", id).getSingleResult();

        session.evict(oldCitizen);

        session.update(citizen);

        return 0;
    }

    @Override
    @Transactional
    public Citizen getEntity(String username){

        Session session = entityManager.unwrap(Session.class);

        Citizen tmp = session.createQuery("SELECT c FROM Citizen c WHERE c.username = :username", Citizen.class).setParameter("username", username).getSingleResult();

        //Citizen citizen = new Citizen(tmp.getFullName(), tmp.getRegion(), tmp.getAddress(), tmp.getPhoneNumber());

        return tmp;
    }



}
