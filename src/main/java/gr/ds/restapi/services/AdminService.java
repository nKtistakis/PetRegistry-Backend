package gr.ds.restapi.services;

import gr.ds.restapi.dao.AdminRepository;
import gr.ds.restapi.entity.Admin;
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
public class AdminService implements AdminRepository {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public List<Admin> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Admin> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Admin> findAllById(Iterable<Integer> integers) {
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
    public void delete(Admin entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Admin> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Admin> S save(S entity) {
        return adminRepository.save(entity);
    }

    @Override
    public <S extends Admin> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Admin> findById(Integer integer) {
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
    public <S extends Admin> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Admin> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Admin> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Admin getOne(Integer integer) {
        return null;
    }

    @Override
    public Admin getById(Integer integer) {
        return adminRepository.getById(integer); //convert to Optional<?>
    }

    @Override
    public <S extends Admin> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Admin> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Admin> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Admin> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Admin> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Admin> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Admin, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
