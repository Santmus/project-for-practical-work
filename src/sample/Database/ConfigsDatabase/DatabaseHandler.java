package sample.Database.ConfigsDatabase;

import sample.Controller.ControllerRegistration;
import sample.Database.InformationUser.User;

import java.sql.*;
/**
 * Данный класс представляет собой запросы при добавлении и авторизация пользователя к БД <b><font color = red>MySQL</font></b>
 * @author Евгений Казаченко
 * @since 1.0.4
 * @version 1.0.5 - SHAPSHOOT
 * */
public class DatabaseHandler extends Configs {

    Connection dbConnection;

    /**
     * Конструктор предназначен для подключение к БД, в случае успеха происходит поключение и последующий <b>SQL</b> - запрос,
     * иначе ошибка о невозможности подключить БД в приложение
     * @since 1.0.4
     * */
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = dbConnect + dbHost
                + dbPort + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        System.out.println("Подключение произошло успешно: " + dbConnect + dbHost + dbPort + dbName + ", " + dbUser + ", " + dbPass);
        return dbConnection;
    }

    /**
     * Метод предназначен для добавление пользователя в <b><font color = red>MySQL</font></b>, возмоджностью дальнейшей авторизации этим пользователем
     * @since 1.0.4
     * @param user передается весь пользователь с необходимыми данными в методе
     * @throws SQLException отсутсвует подключение к <b><font color = red>MySQL</font></b>, в связи с отсутсвием БД в системе
     * @throws ClassNotFoundException не существует такого где происходит поключение к БД
     * @see ControllerRegistration#initialize()
     * */
    public void addUserToDatabase(User user) {
        PreparedStatement addUserToDatabase = null;
        try {
            Connection connection = getDbConnection();
            String addUser = Const.INSERT + Const.EVROSETKA_TABLE
                    + Const.LBRACKET + Const.USER_SURNAME + ","
                    + Const.USER_NAME + "," + Const.USER_LOGIN + ","
                    + Const.USER_PASSWORD + "," + Const.USER_GENDER
                    + Const.RBRACKET + Const.VALUE + "(?,?,?,?,?)";
            addUserToDatabase = connection.prepareStatement(addUser);

            addUserToDatabase.setString(1, user.getSurname());
            addUserToDatabase.setString(2, user.getName());
            addUserToDatabase.setString(3, user.getLogin());
            addUserToDatabase.setString(4, user.getPassword());
            addUserToDatabase.setString(5, user.getGender());

            addUserToDatabase.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
    }
    /**
     * Метод предназначен для авторизации пользователя в <b><font color = red>приложений</font></b>,с возмоджностью дальнейшей работы этим пользователям
     * @since 1.0.4
     * @param user передается весь пользователь с необходимыми данными в методе
     * @see sample.Controller.Controller#getInformationOnEntered(String, String)
     * @throws SQLException отсутсвует подключение к <b><font color = red>MySQL</font></b>, в связи с отсутсвием БД в системе
     * @throws ClassNotFoundException не существует такого где происходит поключение к БД
     * */
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        try {
            Connection connection = getDbConnection();
            String getUser = Const.SELECT + Const.FROM + Const.EVROSETKA_TABLE
                    + Const.WHERE + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD
                    + "=?";

            System.out.println(getUser);

            PreparedStatement addUserToDatabase = connection.prepareStatement(getUser);
            addUserToDatabase.setString(1, user.getLogin());
            addUserToDatabase.setString(2, user.getPassword());

            resultSet = addUserToDatabase.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
        return resultSet;
    }

}
