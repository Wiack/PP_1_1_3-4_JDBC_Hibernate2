package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private String create = "CREATE TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age TINYINT, PRIMARY KEY (id));";
    private String drop = "DROP TABLE IF EXISTS Users;";
    private String save = "INSERT INTO Users (name, lastName, age) VALUES(?,?,?);";
    private String remove = "DELETE FROM Users WHERE id=?;";
    private String allUsers = "select * from users;";
    private String clean = "delete from users;";


    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(create);) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(drop);) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(save);) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            ;
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();


        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(allUsers)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public void cleanUsersTable() {

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(clean);) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
