package com.smu.rest.library.api.controllers.reponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smu.rest.library.dtos.AuthorDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse{
    private String message;
    @JsonProperty("authors")
    private List<AuthorDTO> authorDTOS;
    private AuthorDTO author;

    public AuthorResponse(String message, List<AuthorDTO> authorDTOS, AuthorDTO author) {
        this.message = message;
        this.authorDTOS = authorDTOS;
        this.author = author;
    }


    public String getMessage() {
        return message;
    }

    public List<AuthorDTO> getAuthorDTOS() {
        return authorDTOS;
    }

    public AuthorDTO getAuthor() {
        return author;
    }
}