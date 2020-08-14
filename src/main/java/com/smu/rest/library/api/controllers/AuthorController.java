package com.smu.rest.library.api.controllers;

import com.smu.rest.library.api.controllers.reponses.AuthorResponse;
import com.smu.rest.library.dtos.AuthorDTO;
import com.smu.rest.library.dtos.mappers.AuthorDTOMapper;
import com.smu.rest.library.models.Author;
import com.smu.rest.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors/v1")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty())
            return new ResponseEntity<>(new AuthorResponse("not found",new ArrayList<AuthorDTO>(), null),HttpStatus.OK);

        List<AuthorDTO> authorDTOS = AuthorDTOMapper.mapToList(authors);
        return new ResponseEntity<>(new AuthorResponse("found", authorDTOS, null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable("id") int id) {
        Optional<Author> author = authorRepository.findById(id);
        AuthorDTO authorDTO;
        if (author.isPresent()) {
            authorDTO = AuthorDTOMapper.mapToDTO(author.get());
            return new ResponseEntity<>(new AuthorResponse("found", null, authorDTO), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(new AuthorResponse("not found", null, null), HttpStatus.NOT_FOUND);
        }// end if-else
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponse> deleteAuthorById(@PathVariable("id") int id) {
        Optional<Author> author = authorRepository.findById(id);

        if(author.isEmpty())
            return new ResponseEntity<>(new AuthorResponse("not found", null, null), HttpStatus.NOT_FOUND);

        authorRepository.deleteById(id);
        AuthorDTO authorDTO = AuthorDTOMapper.mapToDTO(author.get());
        AuthorResponse authorResponse = new AuthorResponse("deleted",null, authorDTO);
        return new ResponseEntity<>(authorResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        authorRepository.save(author);
        return new ResponseEntity<>("new Author added with id: "+ author.getId(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AuthorResponse> updateAuthor(@RequestBody Author author) {
        authorRepository.save(author);
        AuthorDTO authorDTO = AuthorDTOMapper.mapToDTO(author);
        return new ResponseEntity<>(new AuthorResponse("successfully updated", null, authorDTO), HttpStatus.OK);
    }


}
