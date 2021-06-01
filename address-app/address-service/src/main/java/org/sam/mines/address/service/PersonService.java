package org.sam.mines.address.service;

import org.sam.mines.address.model.Person;
import org.sam.mines.address.model.Town;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    List<Person> getAll();

    Optional<Person> get(UUID id);

    Person save(Person person);

    void delete(UUID id);


}
