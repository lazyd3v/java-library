package main.entities.dao;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import main.entities.Book;

public class BookDAO extends BaseDAO<Book> {
    public BookDAO() {
        super("db/books.json", new TypeToken<ArrayList<Book>>(){}.getType());
    }
}
