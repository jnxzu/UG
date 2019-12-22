package pl.edu.ug.tent.springmvcdemo.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.ug.tent.springmvcdemo.domain.Person;

public interface PersonManager extends JpaRepository<Person, Integer> {
    <S extends Person> S save(S person);

    Person findById(int id);

    void deleteById(int id);

    void deleteAll();

    @Transactional
    void deleteByFirstName(String firstName);

    Person findOneByFirstName(String firstName);

    Person getOneByFirstName(String firstName);
}
