package org.example.service;

import org.example.manager.BookManager;
import org.example.model.Book;
import org.example.model.User;

public class BookService {


    private final BookManager bookManager = new BookManager();

    public Book addBook(Book newBook, User currentUser) {
        newBook.setAuthorId(currentUser.getId());
        return bookManager.add(newBook, currentUser);
    }
}
