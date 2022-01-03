package lk.ijse.hibernate.dto;

public class LoginDTO {
    private String password;
    private String userName;

    public LoginDTO() {
    }

    public LoginDTO(String password, String userName) {
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
