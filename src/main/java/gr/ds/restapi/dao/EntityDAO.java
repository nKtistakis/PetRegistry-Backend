package gr.ds.restapi.dao;


import javax.transaction.Transactional;
import java.util.List;

public interface EntityDAO<EntityType>  {

    List<EntityType> showALl();

    int addEntity(EntityType entity);

    @Transactional
    int deleteEntityByUsername(int id);

    @Transactional
    int updateEntity(EntityType entity);

    EntityType getEntity(String username);

}
