package lv.autoservice.businesslogic.services.userregistration;

import lv.autoservice.businesslogic.ValidationError;

import java.util.List;

public interface UserRegistrationValidator {
    List<ValidationError> validate(UserRegistrationRequest request);
}
