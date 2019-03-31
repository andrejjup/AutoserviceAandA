package lv.autoservice.businesslogic.autoservicereservation;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class AutoServiceReservationValidationImpl implements AutoServiceReservationValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(AutoServiceReservationRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        validateModel(request.getModel()).ifPresent(errors::add);
        validateWork(request.getWork()).ifPresent(errors::add);
        validateDateTime(request.getDateTime()).ifPresent(errors::add);
        validateEmail(request.getEmail()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateModel(String model) {
        if (model == null || model.isEmpty()) {
            return Optional.of(new ValidationError("model", "This field must be completed!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateWork(String work) {
        if (work == null || work.isEmpty()) {
            return Optional.of(new ValidationError("work", "This field must be completed!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            return Optional.of(new ValidationError("dateTime", "This field must be completed!"));
        } else {
            if (dateTime.length() > 16) {
                return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
            }
            if (!Pattern.matches("^[2][0][1-9][0-9][-][0-1][0-9][-][0-3][0-9][ ][1][0-7][:][0][0]", dateTime)) {
                return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
            }
            else {
                char[] dateTimeArray= dateTime.toCharArray();
                if((dateTimeArray[5]== '0')||((dateTimeArray[5]== '1') && ((dateTimeArray[6]== '0')||(dateTimeArray[6]== '1')||(dateTimeArray[6]== '2')))) {
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '1')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '3')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '4')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '5')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '6')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '7')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '8')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '9')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '1')&&(dateTimeArray[6]== '0')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '1')&&(dateTimeArray[6]== '1')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '1')&&(dateTimeArray[6]== '2')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '2')&&(dateTimeArray[8]== '3')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '2')&&(dateTimeArray[8]== '2')&&(dateTimeArray[9]== '9')) {
                        int userNumber= Integer.parseInt(dateTime.substring(2,4));
                        if (userNumber%4!= 0) {
                            return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                        }
                    }
                    return Optional.empty();
                }
                else {
                    return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                }
            }
        }
    }

    private Optional<ValidationError> validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (!userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "You are not registered!"));
            }
        }
        return Optional.empty();
    }
}

