package lv.autoservice.controller;

import lv.autoservice.businesslogic.autoservicereservation.AutoServiceReservationService;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.services.userlogin.UserLoginService;
import lv.autoservice.businesslogic.services.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}

