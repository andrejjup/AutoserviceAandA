package lv.autoservice.controller;

import lv.autoservice.businesslogic.ValidationError;
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


@Controller
public class LoginController {
    @Autowired
    private UserLoginService userLoginService;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView login(String email, String password) {
        ModelAndView modelAndView = new ModelAndView();
        if ((password == null) && (email == null)) {
            modelAndView.addObject("jumbo", "");
        } else {
            UserLoginRequest userLoginRequest = new UserLoginRequest(email, password);
            UserLoginResponse userLoginResponse = userLoginService.login(userLoginRequest);
            modelAndView.setViewName("login");

            if (userLoginResponse.isSuccess()) {
                logger.info("Login success!");

                modelAndView.setViewName("redirect:/myphotos?usrId=" + userLoginResponse.getUserId().intValue() +"&albumId=" +userLoginResponse.getUserId().intValue());
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
