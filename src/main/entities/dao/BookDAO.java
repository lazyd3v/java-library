package main.entities.dao;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import main.entities.Book;

public class BookDAO {
    private List<Book> books = new ArrayList<Book>();

    private static final Type LIST_DATA_TYPE = new TypeToken<ArrayList<Book>>(){}.getType();
    private static final String BOOKS_DB_PATH = "db/books.json";

    public BookDAO() {
        initializeBooksList();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBook(Integer id) throws NoSuchElementException {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new NoSuchElementException();
    }

    public void saveNewBook(Book book) {
        book.setId(books.size() != 0 ? books.get(books.size() - 1).getId() + 1 : 1);
        books.add(book);
        syncJSON();
    }

    public void updateBook(Book bookToSave) {
        Integer requestedBookId = bookToSave.getId();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(requestedBookId)) {
                books.set(i, bookToSave);
                syncJSON();
                return;
            }
        }
    }

    public void deleteBook(Book bookToDelete) {
        Integer requestedBookId = bookToDelete.getId();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(requestedBookId)) {
                books.remove(i);
                syncJSON();
                return;
            }
        }
    }

    private void initializeBooksList () {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(BOOKS_DB_PATH));
            String str = new String(encoded, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            books = gson.fromJson(str, LIST_DATA_TYPE);
        } catch (IOException e) {
            createBooksListFile();
        }
    }

    private void createBooksListFile () {
        try {
            File f = new File(BOOKS_DB_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            syncJSON();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void syncJSON () {
        File f = new File(BOOKS_DB_PATH);
        try {
            Writer writer = new FileWriter(f);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(books, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
