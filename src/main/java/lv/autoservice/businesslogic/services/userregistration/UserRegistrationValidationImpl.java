package lv.autoservice.businesslogic.services.userregistration;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRegistrationValidationImpl implements UserRegistrationValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(UserRegistrationRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateEmail(request.getEmail()).ifPresent(errors::add);
        validateDuplicateEmail(request.getEmail()).ifPresent(errors::add);
        validateTelephoneNumber(request.getTelephoneNumber()).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of(new ValidationError("password", "This field must be completed!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateTelephoneNumber(String telephoneNumber) {
        if (telephoneNumber == null || telephoneNumber.isEmpty()) {
            return Optional.of(new ValidationError("telephoneNumber", "This field must be completed!"));
        }else{
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "This field must be completed!"));
        }else if (!email.contains("@")){
            return Optional.of(new ValidationError("email", "Must be an email"));
        }else{
            return Optional.empty();
        }
    }
}
