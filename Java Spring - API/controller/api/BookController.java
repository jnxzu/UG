package pl.edu.ug.tent.springmvcdemo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.ug.tent.springmvcdemo.IsbnValidation;
import pl.edu.ug.tent.springmvcdemo.domain.Book;
import pl.edu.ug.tent.springmvcdemo.service.BookManager;

@RestController
public class BookController {

  @Autowired
  BookManager bm;

  @GetMapping("/api/book")
  public List<Book> getBooks() {
    return bm.getAllBooks();
  }

  @PostMapping("/api/book")
  Book addBook(@RequestBody Book book) {
    if (IsbnValidation.check(book.getIsbn())) {
      Book bookToAdd = new Book(book.getTitle(), book.getIsbn(), book.getAuthor());
      bm.addBook(bookToAdd);
      return bookToAdd;
    }
    return null;
  }

  @GetMapping("/api/book/{title}")
  Book getBook(@PathVariable String title) {
    return bm.findByTitle(title);
  }

  @PutMapping("/api/book/{title}")
  void replaceBook(@RequestBody Book book, @PathVariable String title) {
    Book editedBook = new Book(book.getTitle(), book.getIsbn(), book.getAuthor());
    Book bookToEdit = bm.findByTitle(title);
    bm.remove(bookToEdit.getTitle());
    bm.addBook(editedBook);
  }

  @DeleteMapping("/api/book/{title}")
  void deletePerson(@PathVariable String title) {
    bm.remove(title);
  }

}
