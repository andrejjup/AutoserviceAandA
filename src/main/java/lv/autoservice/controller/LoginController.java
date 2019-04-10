package lv.autoservice.controller;

import lv.autoservice.businesslogic.ValidationError;
import lv.autoservice.businesslogic.builder.Service;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.ServiceRepository;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.services.userlogin.UserLoginRequest;
import lv.autoservice.businesslogic.services.userlogin.UserLoginResponse;
import lv.autoservice.businesslogic.services.userlogin.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class LoginController {
    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ServiceRepository serviceRepo;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView login(String email, String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jumbo0", "");
        modelAndView.addObject("jumbo1", "");
        modelAndView.addObject("jumbo2", "");
        modelAndView.addObject("jumbo3", "");
        modelAndView.addObject("jumbo4", "");
        modelAndView.addObject("jumbo5", "");
        modelAndView.addObject("jumbo6", "");
        modelAndView.addObject("jumbo7", "");
        modelAndView.addObject("jumbo8", "");
        if ((password == null) && (email == null)) {
            modelAndView.addObject("jumbo", "");
        } else {
            UserLoginRequest userLoginRequest = new UserLoginRequest(email, password);
            UserLoginResponse userLoginResponse = userLoginService.login(userLoginRequest);
            modelAndView.setViewName("login");

            if (userLoginResponse.isSuccess()) {
                logger.info("Login success!");
                    if (email.equals("autoserviceAandA@gmail.com")) {
                        modelAndView.setViewName("admin");
                        loadAdminPage(modelAndView);
                    }
                    else {
                        modelAndView.setViewName("user");
                        modelAndView.addObject("jumbo_title", "WELCOME, " + email + "!");
                        Optional<Service> serviceOpt= serviceRepo.findByEmail(email);
                            if (serviceOpt.isPresent()) {
                                String userDateTime= serviceOpt.get().getDateTime();
                                String userDate= userDateTime.substring(8,10)+"."+userDateTime.substring(5,7)+"."+userDateTime.substring(0,4);
                                String userTime= userDateTime.substring(11,16);
                                modelAndView.addObject("jumbo0", "_____________________________________________________________________________");
                                modelAndView.addObject("jumbo1", "Model: " + serviceOpt.get().getModel());
                                modelAndView.addObject("jumbo2", "Service: " + serviceOpt.get().getWork());
                                modelAndView.addObject("jumbo3", "Date: " + userDate);
                                modelAndView.addObject("jumbo4", "Time: " + userTime);
                                modelAndView.addObject("jumbo5", "Status: " + serviceOpt.get().getStatus());
                            }
                            else {
                                modelAndView.addObject("jumbo2", "You haven't any reservation!");
                            }
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

    public void loadAdminPage(ModelAndView modelAndView) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        logger.info(dateFormat.format(date));
        modelAndView.addObject("jumbo_time", dateFormat.format(date));

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = dateFormat.format(date) + " 10:00";
        logger.info(dateTime);
        Optional<Service> serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo11", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo12", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo13", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo11", "FREE");
        }

        dateTime = dateFormat.format(date) + " 11:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo21", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo22", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo23", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo21", "FREE");
        }

        dateTime = dateFormat.format(date) + " 12:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo31", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo32", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo33", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo31", "FREE");
        }

        dateTime = dateFormat.format(date) + " 13:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo41", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo42", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo43", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo41", "FREE");
        }

        dateTime = dateFormat.format(date) + " 14:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo51", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo52", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo53", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo51", "FREE");
        }

        dateTime = dateFormat.format(date) + " 15:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo61", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo62", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo63", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo61", "FREE");
        }

        dateTime = dateFormat.format(date) + " 16:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo71", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo72", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo73", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo71", "FREE");
        }

        dateTime = dateFormat.format(date) + " 17:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo81", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo82", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo83", "Phone: " + userOpt.get().getTelephoneNumber());
        }
        else {
            modelAndView.addObject("jumbo81", "FREE");
        }
    }
}
