package lv.autoservice.businesslogic.services.userregistration;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.email.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static lv.autoservice.businesslogic.builder.UserBuilder.createUser;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserRegistrationValidator validator;
    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserRegistrationResponse(validationErrors);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer!<br/> \n");
        sb.append("<br/>\n");
        sb.append("Thank You for Registration!<br/>\n");
        sb.append("<br/>\n");
        sb.append("Best regards,<br/>\n");
        sb.append("Your AutoServiceA&A Team<br/>\n");
        if (!SendEmail.SendMailMessage(sb, request.getEmail())) {
            validationErrors = new ArrayList<>();
            validationErrors.add(new ValidationError("email", "Email error!"));
            return new UserRegistrationResponse(validationErrors);
        }

        User user = createUser()
                .withEmail(request.getEmail())
                .withTelephoneNumber(request.getTelephoneNumber())
                .withPassword(request.getPassword())
                .build();

        userRepository.save(user);

        logger.info("New user with email " + user.getEmail() + " was saved in DB");

        return new UserRegistrationResponse(user.getId());
    }
}
