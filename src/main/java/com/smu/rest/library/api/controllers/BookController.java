package com.smu.rest.library.api.controllers;

import com.smu.rest.library.api.controllers.reponses.AuthorResponse;
import com.smu.rest.library.api.controllers.reponses.BookResponse;
import com.smu.rest.library.dtos.AuthorDTO;
import com.smu.rest.library.dtos.BookDTO;
import com.smu.rest.library.dtos.mappers.AuthorDTOMapper;
import com.smu.rest.library.dtos.mappers.BookDTOMapper;
import com.smu.rest.library.models.Author;
import com.smu.rest.library.models.Book;
import com.smu.rest.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<BookResponse> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty())
            return new ResponseEntity<>(new BookResponse("not found",new ArrayList<BookDTO>(), null), HttpStatus.OK);

        List<BookDTO> bookDTOs = BookDTOMapper.mapToList(books);
        return new ResponseEntity<>(new BookResponse("found", bookDTOs, null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") int id){
        Optional<Book> book = bookRepository.findById(id);
        BookDTO bookDTO;
        if (book.isPresent()) {
            bookDTO = BookDTOMapper.mapToDTO(book.get());
            return new ResponseEntity<>(new BookResponse("found", null, bookDTO), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(new BookResponse("book not found for id: "+ id , null, null), HttpStatus.NOT_FOUND);
        }// end if-else
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable("id") int id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty())
            return new ResponseEntity<>(new BookResponse("not found", null, null), HttpStatus.NOT_FOUND);

        bookRepository.deleteById(id);
        BookDTO bookDTO = BookDTOMapper.mapToDTO(book.get());
        BookResponse bookResponse = new BookResponse("deleted", null, bookDTO);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
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
