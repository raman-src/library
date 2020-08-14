package com.smu.rest.library.api.controllers;

import com.smu.rest.library.api.controllers.reponses.AuthorResponse;
import com.smu.rest.library.api.controllers.reponses.BookResponse;
import com.smu.rest.library.api.controllers.reponses.ErrorResponse;
import com.smu.rest.library.dtos.AuthorDTO;
import com.smu.rest.library.dtos.BookDTO;
import com.smu.rest.library.dtos.mappers.AuthorDTOMapper;
import com.smu.rest.library.dtos.mappers.BookDTOMapper;
import com.smu.rest.library.models.Author;
import com.smu.rest.library.models.Book;
import com.smu.rest.library.repositories.AuthorRepository;
import com.smu.rest.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty())
            return new ResponseEntity<>(new BookResponse("not found", new ArrayList<BookDTO>(), null), HttpStatus.OK);

        List<BookDTO> bookDTOs = BookDTOMapper.mapToList(books);
        return new ResponseEntity<>(new BookResponse("found", bookDTOs, null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") int id) {
        Optional<Book> book = bookRepository.findById(id);
        BookDTO bookDTO;
        if (book.isPresent()) {
            bookDTO = BookDTOMapper.mapToDTO(book.get());
            return new ResponseEntity<>(new BookResponse("found", null, bookDTO), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(new BookResponse("book not found for id: " + id, null, null), HttpStatus.NOT_FOUND);
        }// end if-else
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable("id") int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty())
            return new ResponseEntity<>(new BookResponse("not found", null, null), HttpStatus.NOT_FOUND);

        bookRepository.deleteById(id);
        BookDTO bookDTO = BookDTOMapper.mapToDTO(book.get());
        BookResponse bookResponse = new BookResponse("deleted", null, bookDTO);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        Optional<Author> author = authorRepository.findById(bookDTO.getAuthorId());
        if (author.isEmpty())
            return new ResponseEntity<>("no author found with id:" + bookDTO.getAuthorId(), HttpStatus.BAD_REQUEST);

        Book book = new Book(bookDTO.getTitle(), bookDTO.getPages(), author.get());
        bookRepository.save(book);
        return new ResponseEntity<>("new Book added with id: " + book.getId(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(bookDTO.getId());

        if (book.isEmpty())
            return new ResponseEntity<>(new BookResponse("book not found for id: " + bookDTO.getId(), null, null), HttpStatus.NOT_FOUND);

        Book b = book.get();
        b.setTitle(bookDTO.getTitle());
        b.setPages(bookDTO.getPages());

        bookRepository.save(b);
        return new ResponseEntity<>("successfully updated", HttpStatus.OK);
    }

}
