package com.smu.rest.library.dtos.mappers;

import com.smu.rest.library.dtos.AuthorDTO;
import com.smu.rest.library.dtos.BookDTO;
import com.smu.rest.library.models.Author;
import com.smu.rest.library.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorDTOMapper {

    public static AuthorDTO mapToDTO(Author author) {
        List<Integer> bookIds = getBookIds(author.getBooks());
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName(), bookIds);
    }

    public static List<AuthorDTO> mapToList(List<Author> authors) {
        return authors.stream().map(a -> {
            List<Integer> bookIds = getBookIds(a.getBooks());

            AuthorDTO authorDTO = new AuthorDTO(a.getId(), a.getFirstName(), a.getLastName(), bookIds);
            return authorDTO;
        }).collect(Collectors.toList());
    }

    private static List<Integer> getBookIds(List<Book> books) {
        return books.stream().map(b -> b.getId()).collect(Collectors.toList());
    }

}
