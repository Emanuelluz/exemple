package br.com.example.personcrud;

import br.com.example.personcrud.model.Person;
import br.com.example.personcrud.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService service;

    @Test
    void contextLoadsAndServiceIsInjected() {
        assertThat(service).isNotNull();
        Person p = new Person();
        p.setFirstName("Test");
        p.setLastName("User");
        p.setCpf("00000000000");
        service.save(p);
        assertThat(service.findAll()).isNotEmpty();
    }
}
