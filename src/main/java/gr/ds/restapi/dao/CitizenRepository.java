package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

    @Query("select new Citizen(u.id, c.code, u.username, u.passcode, c.fullName, u.region, c.address, c.phoneNumber, c.email, u.enabled) from User u join Citizen c on u.id = c.id where c.code= ?1")
    Citizen getFullCitizenByCode(int code);

    @Query("select c from Citizen c where c.code = ?1")
    Citizen getCitizenByCode(int code);

}
