package com.example.validForm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MessageSource messageSource;

}
