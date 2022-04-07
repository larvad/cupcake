package dat.startcode.model.entities;

import java.util.Objects;

public class User
{
    private String username;
    private String password;
    private String email;
    private String role;
    private int balance;

    public User(String username, String password, String email, String role, int balance)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.balance = balance;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "brugerNavn='" + username + '\'' +
                ", kodeord='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rolle='" + role + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) &&
                getRole().equals(user.getRole()) && getEmail().equals(user.getEmail()) && (getBalance() == user.getBalance());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUsername(), getPassword(), getEmail(), getRole(), getBalance());
    }
}
