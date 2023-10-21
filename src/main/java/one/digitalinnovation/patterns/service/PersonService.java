package one.digitalinnovation.patterns.service;

import one.digitalinnovation.patterns.dto.PersonDTO;
import one.digitalinnovation.patterns.model.Person;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAll();
    PersonDTO getById(Long id);
    PersonDTO insert(Person person);
    PersonDTO update(Long id, Person person);
    void delete(Long id);
}
