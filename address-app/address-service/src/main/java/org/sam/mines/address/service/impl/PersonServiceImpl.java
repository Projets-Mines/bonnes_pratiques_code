package org.sam.mines.address.service.impl;

import org.sam.mines.address.service.PersonService;
import org.sam.mines.address.model.Person;
import org.sam.mines.address.persistence.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> get(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public Person save(Person person) {
        if (person.getName() == null || person.getName().isBlank()){
            throw new IllegalArgumentException("Name is required");
        }

        return personRepository.save(person);
    }

    @Override
    public void delete(UUID id) {
        personRepository.deleteById(id);
    }
}
