package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {

    //void deleteByUserId(Integer integer);
    //List<Role> findByUserId(Integer integer);

}
