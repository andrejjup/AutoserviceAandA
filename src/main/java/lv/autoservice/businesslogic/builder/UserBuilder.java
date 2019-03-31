package lv.autoservice.businesslogic.builder;

public class UserBuilder {
    private Long id;
    private String password;
    private String email;
    private String telephoneNumber;

    private UserBuilder() {}

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setTelephoneNumber(telephoneNumber);
        user.setPassword(password);
        return user;
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }
}
