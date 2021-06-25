package sample.Database.ConfigsDatabase;

/**
 * <p>Данный класс представляет собой <b>final</b> элементы к запросам при добавлении и авторизация пользователя к БД <b><font color = red>MySQL</font></b></p>
 * @author Евгений Казаченко
 * @since 1.0.4
 * @version 1.0.9 - SHAPSHOOT
 * */
public class Const {

    //example which never used
    public static final String INSERT_NEW_MEMBER_OF_APPLICATION = "INSERT INTO evrosetka_users (user_surname, user_name, user_login, user_password, user_gender) VALUE (?,?,?,?,?)";
    public static final String SELECT_USER_MEMBER = "SELECT user_login, user_password FROM evrosetka_users";

    public static final String INSERT = "INSERT INTO ";
    public static final String SELECT = "SELECT * ";
    public static final String WHERE = "WHERE ";
    public static final String LBRACKET = "(";
    public static final String RBRACKET = ") ";
    public static final String VALUE = "VALUES ";
    public static final String FROM = "FROM ";

    public static final String EVROSETKA_TABLE = "evrosetka_users ";

    public static final String USER_ID = "user_id";
    public static final String USER_SURNAME = "user_surname";
    public static final String USER_NAME = "user_name";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_GENDER = "user_gender";

}
