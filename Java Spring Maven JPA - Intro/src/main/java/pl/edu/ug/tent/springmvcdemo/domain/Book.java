// package pl.edu.ug.tent.springmvcdemo.domain;

// import javax.persistence.*;

// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.util.List;

// @Entity
// @Data
// @NoArgsConstructor
// public class Book {
// private long id;
// private String title;
// private int yop;

// private List<Publisher> publishers;

// public Book(String title, int yop) {
// this.title = title;
// this.yop = yop;
// }

// @ManyToMany
// public List<Publisher> getPublishers() {
// return publishers;
// }

// @Id
// @GeneratedValue
// public long getId() {
// return id;
// }

// public void setId(long id) {
// this.id = id;
// }
// }
