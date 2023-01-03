package gr.ds.restapi.services;

import gr.ds.restapi.dao.CivicRepository;
import gr.ds.restapi.entity.CivicOfficial;
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
public class CivicService implements CivicRepository {

    @Autowired
    CivicRepository civicRepository;


    @Override
    public List<CivicOfficial> findAll() {
        return null;
    }

    @Override
    public List<CivicOfficial> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CivicOfficial> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CivicOfficial> findAllById(Iterable<Integer> integers) {
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
    public void delete(CivicOfficial entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends CivicOfficial> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CivicOfficial> S save(S entity) {
        return civicRepository.save(entity);
    }

    @Override
    public <S extends CivicOfficial> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CivicOfficial> findById(Integer integer) {
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
    public <S extends CivicOfficial> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CivicOfficial> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CivicOfficial> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CivicOfficial getOne(Integer integer) {
        return null;
    }

    @Override
    public CivicOfficial getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends CivicOfficial> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CivicOfficial> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CivicOfficial> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CivicOfficial> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CivicOfficial> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CivicOfficial> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CivicOfficial, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public CivicOfficial getCivicOfficialByCode(Integer code) {
        return civicRepository.getCivicOfficialByCode(code);
    }
}
