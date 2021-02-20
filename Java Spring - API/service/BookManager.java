package pl.edu.ug.tent.springmvcdemo.service;

import pl.edu.ug.tent.springmvcdemo.domain.Book;

import java.util.List;

public interface BookManager {

  void addBook(Book book);

  Book findByTitle(String title);

  List<Book> getAllBooks();

  void remove(String title);
}
