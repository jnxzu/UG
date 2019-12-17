// package pl.edu.ug.tent.springmvcdemo.controller.api;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import pl.edu.ug.tent.springmvcdemo.domain.Person;
// import pl.edu.ug.tent.springmvcdemo.service.PersonManager;

// import java.util.List;

// @RestController
// public class PersonController {

//   @Autowired
//   PersonManager pm;

//   @GetMapping("/api/person")
//   public List<Person> getPersons() {
//     return pm.getAllPersons();
//   }

//   @PostMapping("/api/person")
//   Person addPerson(@RequestBody Person person) {
//     Person personToAdd = new Person(person.getFirstName(), person.getYob(), person.getAddress());
//     pm.addPerson(personToAdd);
//     return personToAdd;
//   }

//   @GetMapping("/api/person/{id}")
//   Person getPerson(@PathVariable String id) {
//     return pm.findById(id);
//   }

//   @PutMapping("/api/person/{id}")
//   Person replacePerson(@RequestBody Person person, @PathVariable String id) {
//     // TODO update
//     return pm.findById(person.getId());
//   }

//   @DeleteMapping("/api/person/{id}")
//   void deletePerson(@PathVariable String id) {
//     pm.remove(id);
//   }

// }
