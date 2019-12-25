package com.migaop.aspects;

import com.migaop.domain.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public aspect CachingAspect {
    private Map<Long, Person> cache;

    public CachingAspect() {
        cache = new HashMap<>();
    }

    pointcut cachedOp(Long id):execution(public Optional<Person> com.migaop.repository.Repository+.findById(*))&&args(id);

    Optional<Person> around(Long id):cachedOp(id){
        Person person = cache.get(id);
        if (person == null) {
            System.out.println("from memory");
            person = proceed(id).get();
            cache.put(person.getId(), person);
        }
        System.out.println("from cache");
        return Optional.of(person);
    }
}
