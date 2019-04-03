package lv.autoservice.controller;

import lv.autoservice.businesslogic.autoservicereservation.AutoServiceReservationService;
import lv.autoservice.businesslogic.database.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AutoServiceReservationService service;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/admin")
    public ModelAndView admin(String function) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("jumbo", "");
        modelAndView.addObject("jumbo2", "");
        modelAndView.addObject("jumbo3", "");
        modelAndView.addObject("jumbo4", "");
        modelAndView.addObject("jumbo5", "");
        modelAndView.addObject("jumbo6", "");
        modelAndView.addObject("jumbo7", "");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        logger.info(dateFormat.format(date));
        modelAndView.addObject("jumbo_time", dateFormat.format(date));

        logger.info("Login success!");
        logger.info(function);
            if (function.equals("In Progress_10")){
                logger.info("Progress!");
            }
            if (function.equals("Done_10")) {
                logger.info("Admin success!");
            }
        return modelAndView;
    }
}
