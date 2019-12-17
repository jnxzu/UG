package pl.edu.ug.tent.springmvcdemo.service;

import org.springframework.stereotype.Service;
import pl.edu.ug.tent.springmvcdemo.domain.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookInMemoryService implements BookManager {

  private static List<Book> books = new ArrayList<>();

  public void addBook(Book book) {
    books.add(book);
  }

  @Override
  public Book findByTitle(String title) {
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    return null;
  }

  public List<Book> getAllBooks() {
    return books;
  }

  @Override
  public void remove(String title) {
    Book toRemove = null;
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        toRemove = book;
        break;
      }
    }
    if (toRemove != null) {
      books.remove(toRemove);
    }
  }
}
