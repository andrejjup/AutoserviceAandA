package lv.autoservice.businesslogic.services.userlogin;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginValidator validator;
    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Override
    public UserLoginResponse login(UserLoginRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserLoginResponse(validationErrors);
        }

        User user = userRepository.findByEmail(request.getEmail()).get();
        logger.info("User " + user.getEmail() + " logged in");

        return new UserLoginResponse(user.getId());
    }
}
