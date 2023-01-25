package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = getConnection();

        Statement statement = null;
        String sql ="CREATE TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT, PRIMARY KEY (id));";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {statement.close();}
            if (connection != null) {connection.close();}
        }


    }

    public void dropUsersTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = null;

        String sql = "DROP TABLE IF EXISTS Users";
        try { statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {statement.close();}
            if (connection != null) {connection.close();}
        }


    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users (name, lastName, age) VALUES(?,?,?);";
        try { preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – "+ name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {preparedStatement.close();}
            if (connection != null) {connection.close();}
        }

    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Users WHERE id=?;";
        try { preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,  id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {preparedStatement.close();}
            if (connection != null) {connection.close();}
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = getConnection();
        List<User> userList = new ArrayList<>();

        String sql ="select * from users";
        Statement statement = null;
        try {

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        if (statement != null) {statement.close();}
        if (connection != null) {connection.close();}
    }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = null;
        String sql = "DROP TABLE IF EXISTS Users";
        String sql2 ="CREATE TABLE  Users(id BIGINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT, PRIMARY KEY (id));";
        try { statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {statement.close();}
            if (connection != null) {connection.close();}
        }

    }

}
