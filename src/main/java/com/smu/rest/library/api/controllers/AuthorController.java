package com.smu.rest.library.api.controllers;

import com.smu.rest.library.dtos.AuthorDTO;
import com.smu.rest.library.dtos.BookDTO;
import com.smu.rest.library.dtos.mappers.AuthorDTOMapper;
import com.smu.rest.library.dtos.mappers.BookDTOMapper;
import com.smu.rest.library.models.Author;
import com.smu.rest.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping
    public List<AuthorDTO> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();

        List<AuthorDTO> authorDTOS = AuthorDTOMapper.mapToList(authors);

        return authorDTOS;
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable("id") int id){
        Author author = authorRepository.findById(id).get();
        AuthorDTO authorDTO = AuthorDTOMapper.mapToDTO(author);
        return authorDTO;
    }

    @DeleteMapping("/{id}")
    public AuthorDTO deleteAuthorById(@PathVariable("id") int id){
        Author author = authorRepository.findById(id).get();
        authorRepository.deleteById(id);
        return AuthorDTOMapper.mapToDTO(author);
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author){
        authorRepository.save(author);
    }

    @PutMapping
    public void updateAuthor(@RequestBody Author author){
        authorRepository.save(author);
    }
}
