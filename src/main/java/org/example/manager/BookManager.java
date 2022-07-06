package org.example.manager;

import lombok.SneakyThrows;
import org.example.db.DBConnectionProvider;
import org.example.model.Book;
import org.example.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BookManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();


    @SneakyThrows
    public Book add(Book book, User author) {
        Book savedBook = new Book();
        User authorById = userManager.getById(author.getId());
        if (authorById.getId() == 0) {
            System.out.println(String.format("We can not fond the user with id = %s", author.getId()));
            return savedBook;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO books (`name`, created, author_id) values (?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setTimestamp(2,
                Timestamp.valueOf(convertToLocalDateViaInstant(book.getCreated())));
        preparedStatement.setInt(3, book.getAuthorId());
        int i = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int id = generatedKeys.getInt(1);
        book.setId(id);
        return book;
    }

    private LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
    @SneakyThrows
    public Book getById(int id) {

        Book book = new Book();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from books where id = ?");
        preparedStatement.setInt(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setCreated(resultSet.getTimestamp("created"));
            book.setAuthor(userManager.getById(resultSet.getInt("author_id")));
            book.setAuthorId(resultSet.getInt("author_id"));
        }
        return book;
    }

    @SneakyThrows
    public Book getByIdWithJoin(int id) {

        Book book = new Book();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT b.id, b.`name`, b.`created`," +
                " b.`author_id`, u.`id` AS userId, u.`name` AS userName, u.surname AS userSurname," +
                " u.email AS userEmail, u.`age` AS userAge FROM books b " +
                "INNER JOIN users u ON b.author_id = u.id " +
                "WHERE b.id = ?");
        preparedStatement.setInt(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setCreated(resultSet.getTimestamp("created"));
            book.setAuthorId(resultSet.getInt("author_id"));

            User author = new User();
            author.setId(resultSet.getInt("userId"));
            author.setName(resultSet.getString("userName"));
            author.setSurname(resultSet.getString("userSurname"));
            author.setEmail(resultSet.getString("userEmail"));
            author.setAge(resultSet.getInt("userAge"));
            book.setAuthor(author);
        }
        return book;
    }


    @SneakyThrows
    public void deleteById(int id) {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books where id = ?");

        preparedStatement.setInt(1, id);
        long execute = preparedStatement.executeLargeUpdate();
        if(execute>0){
            System.out.println("Deleted books with id = "+id);
        }
    }

    @SneakyThrows
    public void update(int id, Book book){
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books b set b.name = ?, b.created =? " +
                "where b.id= ?");
        preparedStatement.setString(1,book.getName());
        preparedStatement.setTimestamp(2, (Timestamp) book.getCreated());
        preparedStatement.setInt(3,id);
        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println(String.format("updated book with id = %s : set data = %s", id, book));
        }
    }

}
