package lv.autoservice.controller;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.Service;
import lv.autoservice.businesslogic.database.ServiceRepository;
import lv.autoservice.businesslogic.services.userlogin.UserLoginRequest;
import lv.autoservice.businesslogic.services.userlogin.UserLoginResponse;
import lv.autoservice.businesslogic.services.userlogin.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class LoginController {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private ServiceRepository serviceRepo;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView login(String email, String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jumbo1", "");
        modelAndView.addObject("jumbo2", "");
        modelAndView.addObject("jumbo3", "");
        modelAndView.addObject("jumbo4", "");
        modelAndView.addObject("jumbo5", "");
        modelAndView.addObject("jumbo6", "");
        modelAndView.addObject("jumbo7", "");
        if ((password == null) && (email == null)) {
            modelAndView.addObject("jumbo", "");
        } else {
            UserLoginRequest userLoginRequest = new UserLoginRequest(email, password);
            UserLoginResponse userLoginResponse = userLoginService.login(userLoginRequest);
            modelAndView.setViewName("login");

            if (userLoginResponse.isSuccess()) {
                logger.info("Login success!");
                    if (email== "autoserviceAandA@gmail.com") {
                        modelAndView.setViewName("admin");
                    }
                    else {
                        modelAndView.setViewName("user");
                        modelAndView.addObject("jumbo1", email);
                        Optional<Service> serviceOpt = serviceRepo.findByEmail(email);
                        modelAndView.addObject("jumbo2", serviceOpt.get().getModel());
                        modelAndView.addObject("jumbo3", serviceOpt.get().getWork());
                        modelAndView.addObject("jumbo4", serviceOpt.get().getDateTime());
                        modelAndView.addObject("jumbo5", "");
                    }
                return  modelAndView;
            } else {
                modelAndView = errorMsg(modelAndView, userLoginResponse.getErrors());
            }
        }
        return modelAndView;
    }

    public ModelAndView errorMsg(ModelAndView modelAndView, List<ValidationError> list) {
        if (list.get(0).getField().equals("email")) {
            if (list.get(0).getErrorMessage().equals("This field must be completed!")) {
                modelAndView.addObject("jumbo", "This field must be completed!");
            } else{
                modelAndView.addObject("jumbo", "Such email not found!");
            }
        }else if (list.get(0).getField().equals("password")) {
            if (list.get(0).getErrorMessage().equals("This field must be completed!")) {
                modelAndView.addObject("jumbo", "Incorrect password field!");
            } else {
                modelAndView.addObject("jumbo", "Incorrect password!");
            }
        }
        return modelAndView;
    }
}
