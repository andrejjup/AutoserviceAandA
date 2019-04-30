package lv.autoservice.controller;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.autoservicereservation.AutoServiceReservationRequest;
import lv.autoservice.businesslogic.autoservicereservation.AutoServiceReservationResponse;
import lv.autoservice.businesslogic.autoservicereservation.AutoServiceReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AutoServiceReservationService service;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/home")
    public ModelAndView home(String model, String wheel, String oil, String other, String time, String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jumbo1", "");
        modelAndView.addObject("jumbo2", "");
        modelAndView.addObject("jumbo3", "");
        modelAndView.addObject("jumbo4", "");
        modelAndView.addObject("jumbo5", "");
        modelAndView.addObject("jumbo6", "");
        modelAndView.addObject("jumbo7", "");
        if ((email != null) && (!email.isEmpty())) {
            Optional<User> userOpt = userRepo.findByEmail(email);
            if (!userOpt.isPresent()) {
                modelAndView.setViewName("registration");
                modelAndView.addObject("jumbo", "You are not registered! Please fill the registration form and repeat the auto service reservation");
                return modelAndView;
            }
            else {
                String work = null;
                if (wheel != null) {
                    work = "wheel";
                } else if (oil != null) {
                        work = "oil";
                } else if (other != null) {
                        work = "other";
                }
                AutoServiceReservationRequest request = new AutoServiceReservationRequest(model, work, time, email);
                AutoServiceReservationResponse response = service.reserve(request);

                if (response.isSuccess()) {
                    logger.info("Your choice is saved, thank You for using our service!");
                    modelAndView.addObject("jumbo6", "Thank You! Your reservation is completed!");
                } else {
                    modelAndView = errMsg(modelAndView, response.getErrors());
                }

                return modelAndView;
            }
        }
        return modelAndView;
    }


    public ModelAndView errMsg(ModelAndView modelAndView, List<ValidationError> list) {
        if (list.get(0).getField().equals("model")) {
            modelAndView.addObject("jumbo7", "This field must be completed!");
        }else if (list.get(0).getField().equals("work")) {
            modelAndView.addObject("jumbo1", "This field must be completed!");
        }
        else if (list.get(0).getField().equals("dateTime")) {
            if (list.get(0).getErrorMessage().equals("This field must be completed!")) {
                modelAndView.addObject("jumbo2", "This field must be completed!");
            } else if (list.get(0).getErrorMessage().equals("Date/Time format error!")) {
                modelAndView.addObject("jumbo3", "Date/Time format error!");
            } else if (list.get(0).getErrorMessage().equals("Input error!")) {
                modelAndView.addObject("jumbo4", "Input error!");
            } else if (list.get(0).getErrorMessage().equals("Sorry, your desired time is booked!")) {
                modelAndView.addObject("jumbo5", "Sorry, your desired time is booked!");
            }
        }
        return modelAndView;
    }
}


