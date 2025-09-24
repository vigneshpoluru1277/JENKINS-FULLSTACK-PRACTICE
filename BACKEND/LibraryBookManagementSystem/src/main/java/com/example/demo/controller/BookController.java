
package com.example.demo.controller;
 // Endpoint to get all books at /springbootlearning/getall
    // @GetMapping("/springbootlearning/getall")
    // public List<Book> getAllBooksExternal() {
    //     return repo.findAll();
    // }

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:5173") // allow React frontend
public class BookController {
    // Jenkins project verification endpoint
    @GetMapping("/springbootlearning/booksapi")
    public String jenkinsProjectStatus() {
        return "jenkins project working successful";
    }
     @GetMapping("/springbootlearning/getall")
    public List<Book> getAllBooksExternal() {
        return repo.findAll();
    }

	
    private final BookRepository repo;
    
    public BookController(BookRepository repo) {
        this.repo = repo;
    }
}

//     package com.example.demo.controller;

//     import java.util.List;

//     import org.springframework.web.bind.annotation.CrossOrigin;
//     import org.springframework.web.bind.annotation.DeleteMapping;
//     import org.springframework.web.bind.annotation.GetMapping;
//     import org.springframework.web.bind.annotation.PathVariable;
//     import org.springframework.web.bind.annotation.PostMapping;
//     import org.springframework.web.bind.annotation.PutMapping;
//     import org.springframework.web.bind.annotation.RequestBody;
//     import org.springframework.web.bind.annotation.RequestMapping;
//     import org.springframework.web.bind.annotation.RestController;

//     import com.example.demo.model.Book;
//     import com.example.demo.repository.BookRepository;

//     @GetMapping
//     public List<Book> getAll() {
//         return repo.findAll();
//     }

//     @PostMapping
//     public Book create(@RequestBody Book book) {
//         return repo.save(book);
//     }


//         // Endpoint to get all books at /springbootlearning/getall
//         @GetMapping("/springbootlearning/getall")
//         public List<Book> getAllBooksExternal() {
//             return repo.findAll();
//         }
//     @PutMapping("/{id}")
//     public Book update(@PathVariable Long id, @RequestBody Book book) {
//         book.setId(id);
//         return repo.save(book);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         repo.deleteById(id);
//     }
// }
