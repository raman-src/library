package com.smu.rest.library.dtos.mappers;

import com.smu.rest.library.dtos.BookDTO;
import com.smu.rest.library.models.Book;
import java.util.List;
import java.util.stream.Collectors;

public class BookDTOMapper {

    public static BookDTO mapToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getPages(), book.getAuthor().getId());
    }

    public static List<BookDTO> mapToList(List<Book> books) {
      return books.stream().map(b -> new BookDTO(b.getId(),b.getTitle(),b.getPages(),b.getAuthor().getId())).collect(Collectors.toList());
    }

}
