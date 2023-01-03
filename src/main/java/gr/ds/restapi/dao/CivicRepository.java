package gr.ds.restapi.dao;

import gr.ds.restapi.entity.CivicOfficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CivicRepository  extends JpaRepository<CivicOfficial, Integer> {

    CivicOfficial getCivicOfficialByCode(Integer code);

}
