package com.migaop.repository;

import com.migaop.domain.Person;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Person> getAll();

    Optional<Person> findById(Long id);

    void save(Person person);

    void update(Person person);

    void delete(Long id);
}
