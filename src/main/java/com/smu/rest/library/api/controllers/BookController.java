package com.smu.rest.library.api.controllers;

import com.smu.rest.library.dtos.BookDTO;
import com.smu.rest.library.dtos.mappers.BookDTOMapper;
import com.smu.rest.library.models.Book;
import com.smu.rest.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return BookDTOMapper.mapToList(books);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable("id") int id){
        Book book = bookRepository.findById(id).get();
        return BookDTOMapper.mapToDTO(book);
    }

    @DeleteMapping("/{id}")
    public BookDTO deleteBookById(@PathVariable("id") int id){
        Book book = bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return BookDTOMapper.mapToDTO(book);
    }

    @PostMapping
    public void addBook(@RequestBody Book book){
        bookRepository.save(book);
    }

    @PutMapping
    public void updateBook(@RequestBody Book book){
        bookRepository.save(book);
    }
}
