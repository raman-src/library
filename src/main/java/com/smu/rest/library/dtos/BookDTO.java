package com.smu.rest.library.dtos;

public class BookDTO {

    private int id;
    private String title;
    private int pages;
    private int authorId;

    public BookDTO(int id, String title, int pages, int authorId) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public int getAuthorId() {
        return authorId;
    }
}
