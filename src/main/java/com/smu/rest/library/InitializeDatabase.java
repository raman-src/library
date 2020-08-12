package com.smu.rest.library;

import com.smu.rest.library.models.Author;
import com.smu.rest.library.models.Book;
import com.smu.rest.library.repositories.AuthorRepository;
import com.smu.rest.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeDatabase implements ApplicationRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private List<Book> book0s = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Author author = new Author("Carl", "Sagan");
        List<Book> books = List.of(new Book("Cosmos", 416, author), new Book("Contact", 385, author));
        saveToDb(author, books);

        author = new Author("George", "Orwell");
        books = List.of(new Book("1984", 433, author), new Book("Animal Farm", 115, author));
        saveToDb(author, books);

        author = new Author("Craig", "Walls");
        books = List.of(new Book("Spring in action", 624, author));
        saveToDb(author, books);

    }


    private void saveToDb(Author author, List<Book> books){

        author.setBooks(books);

        books.stream().forEach( b -> {
            b.setAuthor(author);
        });

        authorRepository.save(author);
    }
}
