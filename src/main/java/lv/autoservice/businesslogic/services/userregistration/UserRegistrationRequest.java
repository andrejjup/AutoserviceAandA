package lv.autoservice.businesslogic.services.userregistration;

public class UserRegistrationRequest {
    private String email;
    private String password;
    private String telephoneNumber;

    public UserRegistrationRequest(String email, String telephoneNumber, String password) {
        this.email = email;
        this.telephoneNumber= telephoneNumber;
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
