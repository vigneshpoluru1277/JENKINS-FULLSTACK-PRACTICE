package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
@RequestMapping("/bookapi/")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home() 
    {
        return "Jenkins Full Stack Deployment Demo";
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
            .<ResponseEntity<?>>map(book -> new ResponseEntity<>(book, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>("Book with ID " + id + " not found.", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        if (book.getId() == null || !bookRepository.existsById(book.getId())) {
            return new ResponseEntity<>("Cannot update. Book with ID " + book.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
        Book updatedBook = bookRepository.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return new ResponseEntity<>("Cannot delete. Book with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book with ID " + id + " deleted successfully.", HttpStatus.OK);
    }
}
