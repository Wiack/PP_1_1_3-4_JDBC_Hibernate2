package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private String sql;

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        sql = "CREATE TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT, PRIMARY KEY (id));";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.execute();
        }


    }

    @Override
    public void dropUsersTable() throws SQLException {
        sql = "DROP TABLE IF EXISTS Users;";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.execute();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        sql = "INSERT INTO Users (name, lastName, age) VALUES(?,?,?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }

    }

    @Override
    public void removeUserById(long id) throws SQLException {
        sql = "DELETE FROM Users WHERE id=?;";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ;
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        sql = "select * from users;";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                userList.add(user);
            }
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        sql = "delete from users;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.executeUpdate();
        }

    }


}
