package pl.edu.ug.tent.springmvcdemo.domain;

import lombok.Data;

@Data
public class Book{
    private String title;
    private String isbn;
    private Person author;

    public Book(String title, String isbn, Person author){
        this.title=title;
        this.isbn=isbn;
        this.author=author;
    }
}