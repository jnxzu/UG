package com.example.validForm.Controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import com.example.validForm.Person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class PersonController {
    private static Logger log = LogManager.getLogger();
    @Autowired
    private ArrayList<Person> people;

    @GetMapping
    public String personForm(final Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @PostMapping
    public String processOrder(@Valid final Person person, final Errors errors) {
        if (errors.hasErrors()) {
            return "personForm";
        }
        people.add(person);
        log.info("Person created: " + person);
        return "redirect:/";
    }
}
