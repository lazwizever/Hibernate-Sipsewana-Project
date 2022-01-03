package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginMaster {
    private String password;
    @Id
    private String userName;

    public LoginMaster() {
    }

    public LoginMaster(String password, String userName) {
        this.setPassword(password);
        this.setUserName(userName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
