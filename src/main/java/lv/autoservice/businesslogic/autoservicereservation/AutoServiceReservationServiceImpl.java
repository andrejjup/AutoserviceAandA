package lv.autoservice.businesslogic.autoservicereservation;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.calendar.CalendarEditor;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.email.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutoServiceReservationServiceImpl implements AutoServiceReservationService {
    @Autowired
    private AutoServiceReservationValidator validator;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AutoServiceReservationResponse reserve(AutoServiceReservationRequest request) {

        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new AutoServiceReservationResponse(validationErrors);
        }

        try {
            if (!CalendarEditor.isPhotoServiceReserved(request.getDateTime())) {
                validationErrors = new ArrayList<>();
                validationErrors.add(new ValidationError("dateTime", "Sorry, your desired time is booked!"));
                return new AutoServiceReservationResponse(validationErrors);
            }
        } catch (Exception e) {
            validationErrors = new ArrayList<>();
            validationErrors.add(new ValidationError("dateTime", "Input error!"));
            return new AutoServiceReservationResponse(validationErrors);
        }

        String dateTimeFormat= request.getDateTime().substring(0,10) + " at" + request.getDateTime().substring(10,16);
        StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer!<br/> \n");
        sb.append("<br/>\n");
        sb.append("Thank You for Your Order!<br/>\n");
        sb.append("We are waiting for Your " + request.getService() + " photo session on " + dateTimeFormat + "!<br/>\n");
        sb.append("<br/>\n");
        sb.append("Best regards,<br/>\n");
        sb.append("Your autoservice Team<br/>\n");
        if (!SendEmail.SendMailMessage(sb, request.getEmail())) {
            validationErrors = new ArrayList<>();
            validationErrors.add(new ValidationError("email", "Email error!"));
            return new AutoServiceReservationResponse(validationErrors);
        }

        User user =  userRepository.findByEmail(request.getEmail()).get();

        return new AutoServiceReservationResponse(user.getId());
    }
}


