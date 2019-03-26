package lv.autoservice.controller;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.services.userregistration.UserRegistrationRequest;
import lv.autoservice.businesslogic.services.userregistration.UserRegistrationResponse;
import lv.autoservice.businesslogic.services.userregistration.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class RegistrationController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/registration")
    public ModelAndView registration(String email, String telephoneNumber, String password, String repeat) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jumbo", "");
        if ((password == null) && (telephoneNumber== null) && (email == null) && (repeat == null)) {

        } else {
            if (password.equals(repeat)) {
                UserRegistrationRequest request = new UserRegistrationRequest(email, telephoneNumber, password);
                UserRegistrationResponse response = userRegistrationService.register(request);
                modelAndView.setViewName("registration");
                if (response.isSuccess()) {
                    logger.info("Registration success!");
                    modelAndView.setViewName("home");
                    modelAndView.addObject("jumbo1", "");
                    modelAndView.addObject("jumbo2", "");
                    modelAndView.addObject("jumbo3", "");
                    modelAndView.addObject("jumbo4", "");
                    modelAndView.addObject("jumbo5", "Thank You! Your registration is completed!");
                    modelAndView.addObject("jumbo6", "");
                    return modelAndView;
                } else {
                    modelAndView = errorMsg(modelAndView, response.getErrors());
                }
            }
            else {
                modelAndView.addObject("jumbo", "Password and Repeat password don't match!");
            }
        }
        return modelAndView;
    }

    public ModelAndView errorMsg(ModelAndView modelAndView, List<ValidationError> list) {
        if (list.get(0).getField().equals("email")) {
            if (list.get(0).getErrorMessage().equals("This field must be completed!")) {
                modelAndView.addObject("jumbo", "This field must be completed!");
            } else if (list.get(0).getErrorMessage().equals("Must not be repeated")) {
                modelAndView.addObject("jumbo", "Such email exists!");
            } else {
                modelAndView.addObject("jumbo", "Email error!");
            }
        } else if (list.get(0).getField().equals("telephoneNumber")) {
            if (list.get(0).getErrorMessage().equals("This field must be completed!")) {
                modelAndView.addObject("jumbo", "Incorrect password field!");
            }
        }else if (list.get(0).getField().equals("password")) {
            if (list.get(0).getErrorMessage().equals("This field must be completed!")) {
                modelAndView.addObject("jumbo", "Incorrect password field!");
            }
        }
        return modelAndView;
    }
}
