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
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("jumbo", "");
        modelAndView.addObject("jumbo2", "");
        modelAndView.addObject("jumbo3", "");
        modelAndView.addObject("jumbo4", "");
        modelAndView.addObject("jumbo5", "");
        modelAndView.addObject("jumbo6", "");
        modelAndView.addObject("jumbo7", "");

        logger.info("Login success!");
        logger.info("Login success!");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        logger.info(dateFormat.format(date));
        modelAndView.addObject("jumbo2", dateFormat.format(date));

        return modelAndView;
    }
}
