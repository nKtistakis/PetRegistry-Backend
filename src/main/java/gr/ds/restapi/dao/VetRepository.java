package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

    Vet getVetByCode(int code);
}
