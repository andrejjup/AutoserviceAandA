package lv.autoservice.businesslogic.autoservicereservation;

import lv.autoservice.businesslogic.ValidationError;

import java.util.List;

public interface AutoServiceReservationValidator {
    List<ValidationError> validate(AutoServiceReservationRequest request);
}
