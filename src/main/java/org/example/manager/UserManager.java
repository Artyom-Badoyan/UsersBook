package org.example.manager;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.db.DBConnectionProvider;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserManager {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    @SneakyThrows
    public User add(User user) {
        PreparedStatement preparedStatement = connection.prepareStatement( //Заявление о подготовке соединения
                "INSERT INTO users (`name`, surname, email, password, age) values(?,?,?,?,? )",
                Statement.RETURN_GENERATED_KEYS); // ВОЗВРАТ СОЗДАННЫХ КЛЮЧЕЙ
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, user.getAge());
        int execute = preparedStatement.executeUpdate(); //выполнить обновление
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); //подготовленный оператор
                                                                        // получает сгенерированные ключи
        generatedKeys.next(); //сгенерированные ключи далее
        int id = generatedKeys.getInt(1);
        user.setId(id);
        if (execute > 0) {
            System.out.println("new user added: " + user);
        }
        return user;
    }


    @SneakyThrows
    public User getById(int id) {

        User user = new User();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setInt(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setEmail(resultSet.getString("email"));
        }
        return user;
    }


    @SneakyThrows
    public void deleteById(int id) {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?");

        preparedStatement.setInt(1, id);
        long execute = preparedStatement.executeLargeUpdate();
        if(execute>0){
            System.out.println("Deleted user with id = "+id);
        }
    }


    @SneakyThrows
    public void update(int id, User user){
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users u set u.name = ?, u.surname =?, u.email = ?, " +
                "u.password= ?, u.age=? where u.id= ?");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getSurname());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setString(4,user.getPassword());
        preparedStatement.setInt(5,user.getAge());
        preparedStatement.setInt(6,id);
        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println(String.format("updated user with id = %s : set data = %s", id, user));
        }
    }


    @SneakyThrows
    public boolean existByEmail(String email){
        boolean exist = false;
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users u where u.email =?");
        preparedStatement.setString(1,email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            exist = true;
        }
        return exist;
    }

    @SneakyThrows
    public User getByEmailAndPassword(String email, String password) {

        User user = new User();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ?");
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setAge(resultSet.getInt("age"));
            user.setEmail(resultSet.getString("email"));
        }
        return user;
    }
}
