package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.entities.dao.*;
import main.entities.*;
import main.enums.UserType;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        // launch(args);
        BookDAO b = new BookDAO();
        Book newBook = new Book();
        newBook.setName("Test book");
        // b.saveNew(newBook);
        // b.deleteBook(newBook);

        UserDAO u = new UserDAO();
        User user = new User();
        user.setName("Admin");
        user.setPassword("12345");
        user.setType(UserType.LIBRARIAN);
        u.saveNew(user);


//        Book savedBook = b.getBook(1);
//        savedBook.setName("edited!");
//        savedBook.setPublisher("Foobar");
//        b.updateBook(savedBook);
    }
}
