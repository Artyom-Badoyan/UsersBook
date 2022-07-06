package org.example;

import lombok.SneakyThrows;
import org.example.excepetions.UserByEmailExistException;
import org.example.excepetions.UserNotFoundException;
import org.example.model.Book;
import org.example.model.User;
import org.example.service.BookService;
import org.example.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Stage {

    private static Scanner scanner = new Scanner(System.in);

    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();
    private User currentUser;


    public void start() {
        System.out.println("Starting users-book project");
        System.out.println();
        firstStage();

    }


    private void firstStage() {
        System.out.println("For registration press 1");
        System.out.println("For Login press 2");
        int command = scanner.nextInt();
        switch (command) {
            case 1: {
                registration();
                break;
            }
            case 2: {
                login();
                break;
            }
            default: {
                System.out.println("Please enter only 1 or 2");
                firstStage();
            }
        }
    }

    private void login() {
        System.out.println("Login precess");

        System.out.println("Input your email");
        String email = scanner.next();

        System.out.println("Input your password");
        String password = scanner.next();
        try {
            currentUser = userService.getByEmailAndPass(email, password);
            bookMenu();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            firstStage();
        }

    }

    private void registration() {
        System.out.println("User registration process:");

        System.out.println("Input your name");
        String name = scanner.next();

        System.out.println("Input your surname");
        String surname = scanner.next();

        System.out.println("Input your email");
        String email = scanner.next();

        System.out.println("Input your password");
        String password = scanner.next();

        System.out.println("Input your age");
        int age = scanner.nextInt();

        try {
            currentUser = userService.register(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .password(password)
                    .build());
            System.out.println("You successfully registered");

            bookMenu();
        } catch (UserByEmailExistException e) {
            System.out.println(e.getMessage());
            registration();
        }
    }


    private void bookMenu() {
        System.out.println("For add new book press 1");
        System.out.println("For view your books press 2");
        int command = scanner.nextInt();
        switch (command) {
            case 1: {
                addBook();
                break;
            }
            case 2: {

                printBooks();
                break;
            }
            default: {
                System.out.println("Please input only 1 or 2");
                bookMenu();
            }
        }
    }

    private void printBooks() {


    }

    @SneakyThrows
    private void addBook() {
        System.out.println(" Add book process");

        System.out.println(" Input book name");
        scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();

        System.out.println("Input book created (dd.MM.yyyy)");

        Date created=new SimpleDateFormat("dd.MM.yyyy").parse(scanner.next());

       Book addedBook =  bookService.addBook(Book.builder()
               .name(bookName)
               .created(created).build(), currentUser);
        System.out.println("Your book is added: ");
        System.out.println(addedBook);
        bookMenu();
    }
}
