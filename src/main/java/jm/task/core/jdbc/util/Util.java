package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД

    public static final  String url = "jdbc:mysql://localhost:3306/mydb";
    public static final String username = "root";
    public static final String password = "root";
    public static final String driver = "com.mysql.cj.jdbc.Driver";





    public Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}