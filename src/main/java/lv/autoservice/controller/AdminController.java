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
        modelAndView.addObject("jumbo1", "");
        modelAndView.addObject("jumbo2", "");
        modelAndView.addObject("jumbo3", "");
        modelAndView.addObject("jumbo4", "");
        modelAndView.addObject("jumbo5", "");
        modelAndView.addObject("jumbo6", "");
        modelAndView.addObject("jumbo7", "");
        modelAndView.addObject("jumbo8", "");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        logger.info(dateFormat.format(date));
        modelAndView.addObject("jumbo_time", dateFormat.format(date));

        logger.info(function);
            if (function.equals("In Progress_10")){
                logger.info("Progress_10!");
            }
            if (function.equals("Done_10")) {
                logger.info("Done_10!");
            }
            if (function.equals("In Progress_11")){
                logger.info("Progress_11!");
            }
            if (function.equals("Done_11")) {
                logger.info("Done_11!");
            }
            if (function.equals("In Progress_12")){
                logger.info("Progress_12!");
            }
            if (function.equals("Done_12")) {
                logger.info("Done_12!");
            }
            if (function.equals("In Progress_13")){
                logger.info("Progress_13!");
            }
            if (function.equals("Done_13")) {
                logger.info("Done_13!");
            }
            if (function.equals("In Progress_14")){
                logger.info("Progress_14!");
            }
            if (function.equals("Done_14")) {
                logger.info("Done_14!");
            }
            if (function.equals("In Progress_15")){
                logger.info("Progress_15!");
            }
            if (function.equals("Done_15")) {
                logger.info("Done_15!");
            }
            if (function.equals("In Progress_16")){
                logger.info("Progress_16!");
            }
            if (function.equals("Done_16")) {
                logger.info("Done_16!");
            }
            if (function.equals("In Progress_17")){
                logger.info("Progress_17!");
            }
            if (function.equals("Done_17")) {
                logger.info("Done_17!");
            }
        return modelAndView;
    }
}
