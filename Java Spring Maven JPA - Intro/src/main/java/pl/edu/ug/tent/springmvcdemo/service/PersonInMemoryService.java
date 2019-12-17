// package pl.edu.ug.tent.springmvcdemo.service;

// import org.springframework.stereotype.Service;
// import pl.edu.ug.tent.springmvcdemo.domain.Person;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// @Service
// public class PersonInMemoryService implements PersonManager {

// private static List<Person> persons = new ArrayList<>();

// public void addPerson(Person person) {
// person.setId(UUID.randomUUID().toString());
// persons.add(person);
// }

// @Override
// public Person findById(String id) {
// for (Person person : persons) {
// if (person.getId().equals(id)) {
// return person;
// }
// }
// return null;
// }

// public List<Person> getAllPersons() {
// return persons;
// }

// @Override
// public void remove(String id) {

// Person toRemove = null;
// for (Person person : persons) {
// if (person.getId().equals(id)) {
// toRemove = person;
// break;
// }
// }
// if (toRemove != null) {
// persons.remove(toRemove);
// }
// }

// @Override
// public List<Person> findByFirstName(String firstName) {
// List<Person> result = new ArrayList<>();
// for (Person person : persons) {
// if (firstName.equals(person.getFirstName())) {
// result.add(person);
// }
// }
// return result;
// }
// }
