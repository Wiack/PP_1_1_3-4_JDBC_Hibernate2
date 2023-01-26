package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        //Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) ( User с именем – name добавлен в базу данных ).
        userService.saveUser("Алла", "Полякова", (byte) 10);
        userService.saveUser("Борис", "Васильев", (byte) 20);
        userService.saveUser("Виктория", "Токарева", (byte) 30);
        userService.saveUser("Григорий", "Чернецов", (byte) 40);

        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        for (User i : userService.getAllUsers()) {
            System.out.println(i);
        }


        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();


    }
}

