package MusicAppSpringGradle.models.bindings;

import MusicAppSpringGradle.models.validators.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
//@FieldMatch(
//        first = "password",
//        second = "confirmPassword"
//)
public class UserRegistrationBindingModel {
@Size(min = 3)
@NotEmpty
    private String username;
@Email
@NotEmpty
    private String email;
    @Size(min = 3)
    @NotEmpty
    private String fullName;
    @Size(min = 5,max = 20)
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
