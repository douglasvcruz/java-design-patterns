package one.digitalinnovation.patterns.service.impl;

import one.digitalinnovation.patterns.dto.AddressDTO;
import one.digitalinnovation.patterns.dto.PersonDTO;
import one.digitalinnovation.patterns.model.Address;
import one.digitalinnovation.patterns.model.AddressRepository;
import one.digitalinnovation.patterns.model.Person;
import one.digitalinnovation.patterns.model.PersonRepository;
import one.digitalinnovation.patterns.service.CepService;
import one.digitalinnovation.patterns.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CepService cepService;

    @Override
    public List<PersonDTO> getAll() {
        Iterable<Person> people = personRepository.findAll();
        return StreamSupport.stream(people.spliterator(), false)
                .map((person) -> new PersonDTO() {
                    {
                        id = person.getId();
                        name = person.getName();
                        age = person.getAge();
                        address = new AddressDTO() {
                            {
                                cep = person.getAddress().getCep();
                                logradouro = person.getAddress().getLogradouro();
                                bairro = person.getAddress().getBairro();
                                localidade = person.getAddress().getLocalidade();
                                uf = person.getAddress().getUf();
                                ddd = person.getAddress().getDdd();
                            }
                        };
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public PersonDTO getById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        Person person1 = person.get();
        return new PersonDTO() {
            {
                id = person1.getId();
                name = person1.getName();
                age = person1.getAge();
                address = new AddressDTO() {
                    {
                        cep = person1.getAddress().getCep();
                        logradouro = person1.getAddress().getLogradouro();
                        bairro = person1.getAddress().getBairro();
                        localidade = person1.getAddress().getLocalidade();
                        uf = person1.getAddress().getUf();
                        ddd = person1.getAddress().getDdd();
                    }
                };
            }
        };
    }

    @Override
    public PersonDTO insert(Person person) {
        return savePersonWithCep(person);
    }

    @Override
    public PersonDTO update(Long id, Person person) {
        Optional<Person> person1 = personRepository.findById(id);
        if (person1.isPresent()) {
            return savePersonWithCep(person);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    private PersonDTO savePersonWithCep(Person person) {
        String cep = person.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = cepService.consultCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });

        person.setAddress(address);
        personRepository.save(person);
        return new PersonDTO() {
            {
                id = person.getId();
                name = person.getName();
                age = person.getAge();
                address = new AddressDTO() {
                    {
                        cep = person.getAddress().getCep();
                        logradouro = person.getAddress().getLogradouro();
                        bairro = person.getAddress().getBairro();
                        localidade = person.getAddress().getLocalidade();
                        uf = person.getAddress().getUf();
                        ddd = person.getAddress().getDdd();
                    }
                };
            }
        };
    }
}
