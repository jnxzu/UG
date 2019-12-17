package com.example.validForm;

import lombok.Data;

import javax.validation.constraints.*;

import com.example.validForm.Validator.PostCode;
import com.example.validForm.Validator.Salary;

@Data
public class Person {
    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name should be start at least two characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be at least zero")
    private int age;

    @NotNull(message = "Postcode is required")
    @PostCode
    private String postcode;

    @NotNull(message = "Salary is required")
    @Salary
    private int salary;

    @AssertTrue(message = "Must accept")
    private boolean accept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    @Override
    public String toString() {
        return "Person [accept=" + accept + ", age=" + age + ", name=" + name + ", postcode=" + postcode + ", salary="
                + salary + "]";
    }

}

