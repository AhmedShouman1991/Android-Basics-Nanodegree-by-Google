package com.example.googlebookssearch;

public class Book {
    private String title;
    private String AuthorName;

    public Book(String title, String authorName) {
        this.title = title;
        AuthorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return AuthorName;
    }
}
