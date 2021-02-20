package com.example.validForm.Controllers;

import com.example.validForm.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/create")
public class PersonController {

    private final JavaMailSender emailSender;

    @Autowired
    public PersonController(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    @GetMapping
    public String personForm(Model model){
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @PostMapping
    public String processOrder(@Valid Person person, Errors errors){
        if(errors.hasErrors()){
            return "personForm";
        }
        log.info("Person created: " + person);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("janbielowka@gmail.com");
        mailMessage.setSubject("Create person");
        mailMessage.setText("Person created: " + person);
        emailSender.send(mailMessage);
        return "redirect:/";
    }
}
