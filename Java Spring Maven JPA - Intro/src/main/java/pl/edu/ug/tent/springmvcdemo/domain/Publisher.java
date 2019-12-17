// package pl.edu.ug.tent.springmvcdemo.domain;

// import javax.persistence.*;
// import java.util.List;

// @Entity
// public class Publisher {

// private long id;
// private String name;
// private int yof;

// private List<Book> books;

// public Publisher(String name, int yof) {
// this.name = name;
// this.yof = yof;
// }

// public Publisher() {
// }

// @ManyToMany(mappedBy = "publishers")
// public List<Book> getBooks() {
// return books;
// }

// public void setBooks(List<Book> books) {
// this.books = books;
// }

// @Id
// @GeneratedValue
// public long getId() {
// return id;
// }

// public void setId(long id) {
// this.id = id;
// }

// public String getName() {
// return name;
// }

// public void setName(String name) {
// this.name = name;
// }

// public int getYof() {
// return yof;
// }

// public void setYof(int yof) {
// this.yof = yof;
// }
// }
