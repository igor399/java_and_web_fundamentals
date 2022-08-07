package by.epam.lab.beans;

import static by.epam.lab.constants.GlobalConstants.*;

public class User {
    private int id;
    private String account;

    public User() {
    }

    public User(int id, String account) {
        this.id = id;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && account.equals(user.account);
    }

    @Override
    public int hashCode() {
        return 17 * id + account.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + SEMICOLON + id + SEMICOLON + account;
    }
}
