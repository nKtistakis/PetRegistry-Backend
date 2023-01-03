package gr.ds.restapi.services;

import gr.ds.restapi.dao.PetRepository;
import gr.ds.restapi.entity.Pet;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PetService implements PetRepository {

    @Autowired
    PetRepository petRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Pet> getPetsByCitizenName(String username) {

        List<Pet> pets  = petRepository.getPetsByCitizenName(username);
        return pets;
    }

    @Override
    public List<Pet> getPendingPetsByUserName(String username) {

        List<Pet> pets  = petRepository.getPendingPetsByUserName(username);
        return pets;
    }

    @Override
    public List<Pet> getPendingPetsByCitizenName(String citizenName) {
        List<Pet> pets  = petRepository.getPendingPetsByCitizenName(citizenName);
        return pets;
    }

    @Override
    public List<Pet> getPetsByRegion(String region) {
        List<Pet> pets = petRepository.getPetsByRegion(region);
        return pets;
    }

    @Override
    public void verifyPet(int petId) {
        petRepository.verifyPet(petId);
    }



    @Override
    public List<Pet> findAll() {
        return null;
    }

    @Override
    public List<Pet> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Pet> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Pet> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        petRepository.deleteById(integer);
    }

    @Override
    public void delete(Pet entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pet> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Pet> S save(S entity) {

        Session session = entityManager.unwrap(Session.class);

        session.save(entity);
        session.close();
        return entity;
    }

    @Override
    public <S extends Pet> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Pet> findById(Integer integer) {

        return petRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Pet> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Pet> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Pet> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Pet getOne(Integer integer) {
        return null;
    }

    @Override
    public Pet getById(Integer integer) {
        return petRepository.getById(integer);
    }

    @Override
    public <S extends Pet> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Pet> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Pet> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Pet> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Pet> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Pet> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Pet, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
