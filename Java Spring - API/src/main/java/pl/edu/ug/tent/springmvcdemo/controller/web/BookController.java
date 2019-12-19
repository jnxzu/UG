package pl.edu.ug.tent.springmvcdemo.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.ug.tent.springmvcdemo.service.BookManager;

@Controller("bookwebcontroller")
public class BookController {

  private BookManager bm;

  @Autowired
  public BookController(BookManager bm) {
    this.bm = bm;
  }

  @GetMapping("/book")
  public String home(Model model) {
    model.addAttribute("books", bm.getAllBooks());
    return "book-all";
  }
}
