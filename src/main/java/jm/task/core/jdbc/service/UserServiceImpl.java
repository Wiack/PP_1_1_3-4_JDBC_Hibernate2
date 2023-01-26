package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDaoJDBC = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        try {
            userDaoJDBC.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            userDaoJDBC.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBC.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            userDaoJDBC.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDaoJDBC.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            userDaoJDBC.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
