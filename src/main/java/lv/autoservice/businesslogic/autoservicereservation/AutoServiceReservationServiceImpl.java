package lv.autoservice.businesslogic.autoservicereservation;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.Service;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.calendar.CalendarEditor;
import lv.autoservice.businesslogic.database.ServiceRepository;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.email.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static lv.autoservice.businesslogic.builder.ServiceBuilder.createService;

@Component
public class AutoServiceReservationServiceImpl implements AutoServiceReservationService {
    @Autowired
    private AutoServiceReservationValidator validator;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;

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

        String fullWorkName= null;
        if (request.getWork().equals("wheel")) {
            fullWorkName= "Wheel Replacement";
        }
        else if (request.getWork().equals("oil")) {
            fullWorkName= "Change of Oil";
        }
        else {
            fullWorkName= "Other";
        }

        String dateTimeFormat= request.getDateTime().substring(0,10) + " at" + request.getDateTime().substring(10,16);
        StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer!<br/> \n");
        sb.append("<br/>\n");
        sb.append("Thank You for Your Order!<br/>\n");
        sb.append("We are waiting for Your " + fullWorkName + " session on " + dateTimeFormat + "!<br/>\n");
        sb.append("<br/>\n");
        sb.append("Best regards,<br/>\n");
        sb.append("Your AutoServiceA@A Team<br/>\n");
        if (!SendEmail.SendMailMessage(sb, request.getEmail())) {
            validationErrors = new ArrayList<>();
            validationErrors.add(new ValidationError("email", "Email error!"));
            return new AutoServiceReservationResponse(validationErrors);
        }

        String workStatus= "Not Started";
        Service service = createService()
                .withModel(request.getModel())
                .withWork(fullWorkName)
                .withDateTime(request.getDateTime())
                .withEmail(request.getEmail())
                .withStatus(workStatus)
                .build();

        serviceRepository.save(service);

        User user =  userRepository.findByEmail(request.getEmail()).get();

        return new AutoServiceReservationResponse(user.getId());
    }
}


