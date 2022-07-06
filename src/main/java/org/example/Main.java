package org.example;

import org.example.db.DBConnectionProvider;
import org.example.manager.BookManager;
import org.example.manager.UserManager;
import org.example.model.Book;
import org.example.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        Stage stage = new Stage();

        stage.start();




//        BookManager bookManager = new BookManager();
//        Book byId = bookManager.getByIdWithJoin(1);
//        System.out.println(byId);


//        User user = new User();
//        Class<? extends User> classUser = user.getClass();
//        Method[] declaredMethods = classUser.getDeclaredMethods();
//        for (Method declaredMethod : declaredMethods) {
//            declaredMethod.setAccessible(true);
//            System.out.println(declaredMethod.getName());
//            if (declaredMethod.getParameterCount()==0)
//                declaredMethod.invoke(user);
//        }

//        Class<User> aClass = (Class<User>) Class.forName("org.example.model.User");
//        Field[] fields = aClass.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            System.out.println(field.getName());
//        }
//        UserManager userManager = new UserManager();
//        User byId = userManager.getById(10);
//        System.out.println(byId);
//        BookManager bookManager = new BookManager();
//        bookManager.add(new Book(),new User(5000,"Poxos","Poxosyan"))
//        userManager.deleteById(6);
//        userManager.update(7, User.builder()
//                        .name("Margarita")
//                        .surname("Murazyan")
//                        .age(27)
//                        .password("147852")
//                        .email("murazyan.margarita@gmail.com")
//                .build());
//        User user4 = userManager.getById(4);
//        User user5 = userManager.getById(5);
//        User user6 = userManager.getById(6);
//        System.out.println(user4);
//        System.out.println(user5);
//        System.out.println(user6);
//        User add = userManager.add(User.builder()
//                .name("Poxos")
//                .surname("Poxosyan")
//                .email("poxos.poxosyan@gmail54.com")
//                .age(18)
//                .password("123456")
//                .build());

//        System.out.println("User from db "+add);

//        DBConnectionProvider provider = DBConnectionProvider.getInstance();
//        Connection connection = provider.getConnection();
//
//
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery("select * from users");
//
//        while (resultSet.next()){
//            System.out.println("user info");
//            User user = new User();
//            user.setId(resultSet.getInt("id"));
//            user.setName(resultSet.getString("user_name"));
//            user.setSurname(resultSet.getString("user_surname"));
//            System.out.println(user);
//        }
    }
};
