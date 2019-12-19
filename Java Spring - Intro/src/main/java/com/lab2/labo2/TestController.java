package com.lab2.labo2;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TestController{
    @RequestMapping("/")
    public String index(){
        return "Jan Bielowka<br>"+"<img src='images/b_COFWmO_400x400.png' alt=''>";
    }

    @RequestMapping(value="/witaj", method=RequestMethod.GET)
    public String witajName(@RequestParam(defaultValue = "Swiecie") String name){
        return "Witaj "+name+"!!!";
    }
}