package sample.Database.InformationUser;

/**
 * Данный класс представляет собой данные об пользователе, которые используется для вызовов запросов
 * @author Евгений Казаченко
 * @since 1.0.5
 * @version 1.0.5 - SHAPSHOOT
 * */
public class User {
    private String surname;
    private String name;
    private String login;
    private String password;
    private String gender;

    /**
     * Данный конструктор предназначен для создания пользовователя - для записи к БД
     * @param gender пол пользователя
     * @param login логин пользователя
     * @param name имя пользователя
     * @param password пароль пользователя
     * @param surname фамилия пользователя
     * @since 1.0.5
     * */
    public User(String surname, String name, String login, String password, String gender) {
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.gender = gender;
    }

    /**
     * Данный констрктор предназначен для создания пользователя - для авторизации к приложению
     * */
    public User(){

    }

    // Получение или приствоение различных полей
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
