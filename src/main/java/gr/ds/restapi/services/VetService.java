package gr.ds.restapi.services;

import gr.ds.restapi.dao.VetRepository;
import gr.ds.restapi.entity.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class VetService implements VetRepository {


    @Autowired
    VetRepository vetRepository;

    @Override
    public List<Vet> findAll() {
        return null;
    }

    @Override
    public List<Vet> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Vet> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Vet> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Vet entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Vet> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Vet> S save(S entity) {
        return vetRepository.save(entity);
    }

    @Override
    public <S extends Vet> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Vet> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Vet> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Vet> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Vet> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Vet getOne(Integer integer) {
        return null;
    }

    @Override
    public Vet getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Vet> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Vet> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Vet> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Vet> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Vet> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Vet> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Vet, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Vet getVetByCode(int code) {
        return vetRepository.getVetByCode(code);
    }
}
