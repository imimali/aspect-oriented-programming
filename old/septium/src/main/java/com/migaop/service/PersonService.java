package com.migaop.service;

import com.migaop.domain.Person;
import com.migaop.repository.Repository;

import java.util.List;
import java.util.Optional;

public class PersonService {
    private Repository personRepository;

    public PersonService(Repository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.getAll();
    }

    public Person findById(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        return personOptional.orElseThrow(() -> new RuntimeException("Id does not exist"));
    }

    public void add(Person person) {
        personRepository.save(person);
    }

    public void update(Person person) {
        personRepository.update(person);
    }

    public void delete(Long id) {
        personRepository.delete(id);
    }
}
