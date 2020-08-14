package com.smu.rest.library.api.controllers.reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smu.rest.library.dtos.BookDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    private String message;
    @JsonProperty("books")
    private List<BookDTO> bookDTOs;
    private BookDTO bookDTO;

    public BookResponse(String message, List<BookDTO> bookDTOs, BookDTO bookDTO) {
        this.message = message;
        this.bookDTOs = bookDTOs;
        this.bookDTO = bookDTO;
    }

    public String getMessage() {
        return message;
    }

    public List<BookDTO> getBookDTOs() {
        return bookDTOs;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }
}
