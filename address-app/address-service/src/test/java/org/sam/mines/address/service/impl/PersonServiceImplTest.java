package org.sam.mines.address.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sam.mines.address.model.Person;
import org.sam.mines.address.persistence.PersonRepository;
import org.sam.mines.address.service.PersonService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService = new PersonServiceImpl(personRepository);

    @Test
    void shouldNotSaveWhenNameIsBlank() {
        // GIVEN
        Person person = Person.PersonBuilder.aPerson().build();
        // WHEN
        // THEN
        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }

    @Test
    void shouldSaveAValidPerson() {
        // GIVEN
        Person person = Person.PersonBuilder.aPerson().withName("Jean-Jacques").build();

        // WHEN
        UUID generatedId = UUID.randomUUID();
        when(personRepository.save(any(Person.class)))
                .thenReturn(Person.PersonBuilder.aPerson().withId(generatedId).build());

        // THEN
        Person persisted = personService.save(person);
        assertEquals(persisted.getId(), generatedId);
    }

    @Test
    void shouldGetAll() {
        // GIVEN
        when(personRepository.findAll()).thenReturn(List.of(
                Person.PersonBuilder.aPerson().withName("person 1").withId(UUID.randomUUID()).build(),
                Person.PersonBuilder.aPerson().withName("person 2").withId(UUID.randomUUID()).build()
        ));

        // WHEN
        List<Person> all = personService.getAll();

        // THEN
        assertEquals(2, all.size());
    }



}