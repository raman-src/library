package com.smu.rest.library.api.controllers;

import com.smu.rest.library.models.Author;
import com.smu.rest.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }


    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable("id") int id){
        return authorRepository.findById(id).get();
    }

    @DeleteMapping("/authors/{id}")
    public Author deleteAuthorById(@PathVariable("id") int id){
        Author author = authorRepository.findById(id).get();
        System.out.println(author.getFirstName());
        authorRepository.deleteById(id);
        return author;
    }



}
