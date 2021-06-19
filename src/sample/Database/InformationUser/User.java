package sample.Database.InformationUser;

public class User {
    private String surname;
    private String name;
    private String login;
    private String password;
    private String gender;

    public User(String surname, String name, String login, String password, String gender) {
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.gender = gender;
    }

    public User(){

    }

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
