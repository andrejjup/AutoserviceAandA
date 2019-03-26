package lv.autoservice.businesslogic.services.userlogin;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserLoginValidatorImpl implements UserLoginValidator {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ValidationError> validate(UserLoginRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateEmail((request.getEmail())).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        validateEmailWithPassword(request.getEmail(), request.getPassword()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of(new ValidationError("password", "This field must be completed!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateEmailWithPassword(String email, String password) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (!userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Such email not found"));
            }
            if (!userOpt.get().getPassword().equals(password)){
                return  Optional.of(new ValidationError("password", "Incorrect password"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "This field must be completed!"));
        }else {
            return Optional.empty();
        }
    }
}
