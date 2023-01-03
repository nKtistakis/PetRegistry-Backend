package gr.ds.restapi.dao;

import gr.ds.restapi.entity.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {


    @Query("select new Pet(p.serialNumber, p.birthDate, p.race, p.sex, p.type, p.is_approved) from Pet p join Citizen c on p.ownerCode = c.id where c.username= ?1")
    List<Pet> getPetsByCitizenName(String username);

    @Query("select new Pet(p.serialNumber, p.birthDate, p.race, p.sex, p.type, p.is_approved, p.ownerCode) from Pet p join Citizen c on p.ownerCode = c.id join User u on u.id=c.id where u.username= ?1 and p.is_approved=0")
    List<Pet> getPendingPetsByUserName(String username);

    @Query("select new Pet(p.serialNumber, p.birthDate, p.race, p.sex, p.type, p.is_approved) from Pet p join Citizen c on p.ownerCode = c.id where c.fullName= ?1 and p.is_approved=0")
    List<Pet> getPendingPetsByCitizenName(String citizenName);


    @Query("select new Pet(p.serialNumber, p.birthDate, p.race, p.sex, p.type, p.is_approved, p.ownerCode) from Pet p join Citizen c on p.ownerCode = c.id  where c.region=?1")
    List<Pet> getPetsByRegion(String region);



    @Transactional
    @Modifying
    @Query("update Pet p set p.is_approved = 1 where p.serialNumber = ?1")
    void verifyPet(int petId);

}
