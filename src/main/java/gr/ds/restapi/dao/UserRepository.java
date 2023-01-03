package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Role;
import gr.ds.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("delete from User u where u.username=?1")
    void deleteUserByUsername(String username);

    @Query("select u from User u where u.username=?1")
    User getUserByUsername(String username);

    User findByUsername(String username);
}
