package lv.autoservice.businesslogic.services.userlogin;
import lv.autoservice.businesslogic.ValidationError;

import java.util.List;

public interface UserLoginValidator {
    List<ValidationError> validate(UserLoginRequest request);
}
