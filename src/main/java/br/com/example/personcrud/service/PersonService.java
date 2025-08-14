package br.com.example.personcrud.service;

import br.com.example.personcrud.model.Person;
import br.com.example.personcrud.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
