package com.example.validForm.Controllers;

import java.util.ArrayList;

import com.example.validForm.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class PeopleController {
    @Autowired
    private ArrayList<Person> people;

    @GetMapping
    public String personForm(final Model model) {
        model.addAttribute("people", people);
        return "peopleList";
    }
}
