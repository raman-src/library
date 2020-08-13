package com.smu.rest.library.dtos;

import com.smu.rest.library.models.Book;

import java.util.List;

public class AuthorDTO {

    private int id;
    private String firstName;
    private String lastName;

    private List<Integer> bookIds;

    public AuthorDTO(int id, String firstName, String lastName, List<Integer> bookIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookIds = bookIds;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }
}
