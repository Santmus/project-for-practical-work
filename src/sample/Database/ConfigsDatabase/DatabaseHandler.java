package sample.Database.ConfigsDatabase;

import sample.Database.InformationUser.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = dbConnect + dbHost
                + dbPort + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        System.out.println("Подключение произошло успешно: " + dbConnect + dbHost + dbPort + dbName + ", " + dbUser + ", " + dbPass);
        return dbConnection;
    }

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

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        try {
            Connection connection = getDbConnection();
            String getUser = Const.SELECT + Const.FROM + Const.EVROSETKA_TABLE
                    + Const.WHERE + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD
                    + "=?";

            //System.out.println(getUser);

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
