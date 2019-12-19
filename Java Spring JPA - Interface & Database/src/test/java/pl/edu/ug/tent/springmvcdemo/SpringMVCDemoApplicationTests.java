package pl.edu.ug.tent.springmvcdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.edu.ug.tent.springmvcdemo.domain.Person;
import pl.edu.ug.tent.springmvcdemo.service.PersonManager;

@SpringBootTest
class SpringMVCDemoApplicationTests {
    @Autowired
    PersonManager menago;

    @Test
    void shouldCreateRead() {
        assertNotNull(menago);
        Person botowski = new Person("botowski", 2018);
        menago.save(botowski);
        Person result = menago.findOneByFirstName("botowski");
        assertNotNull(result);
        assertEquals(result, botowski);
        menago.deleteAll();
    }

    @Test
    void shouldCreateUpdateRead() {
        assertNotNull(menago);
        Person bocinski = new Person("bocinski", 2018);
        menago.save(bocinski);
        Person result = menago.getOneByFirstName("bocinski");
        result.setYob(2019);
        menago.save(result);
        result = menago.findOneByFirstName("bocinski");
        assertNotEquals(result.getYob(), bocinski.getYob());
        menago.deleteAll();
    }

    @Test
    void shouldCreateDeleteRead() {
        assertNotNull(menago);
        Person botuwa = new Person("botuwa", 2018);
        menago.save(botuwa);
        menago.deleteByFirstName("botuwa");
        Person check = menago.findOneByFirstName("botuwa");
        assertNull(check);
        menago.deleteAll();
    }
}