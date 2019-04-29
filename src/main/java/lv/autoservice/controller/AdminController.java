package lv.autoservice.controller;

import lv.autoservice.businesslogic.autoservicereservation.AutoServiceReservationService;
import lv.autoservice.businesslogic.builder.Service;
import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.ServiceRepository;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.email.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ServiceRepository serviceRepo;

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
        redrawAdminPage(modelAndView);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        logger.info(function);
            if (function.equals("In Progress_10")){
                logger.info("Progress_10!");
                String dateTime = dateFormat.format(date) + " 10:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_10")) {
                logger.info("Done_10!");
                String dateTime = dateFormat.format(date) + " 10:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_11")){
                logger.info("Progress_11!");
                String dateTime = dateFormat.format(date) + " 11:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_11")) {
                logger.info("Done_11!");
                String dateTime = dateFormat.format(date) + " 11:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_12")){
                logger.info("Progress_12!");
                String dateTime = dateFormat.format(date) + " 12:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_12")) {
                logger.info("Done_12!");
                String dateTime = dateFormat.format(date) + " 12:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_13")){
                logger.info("Progress_13!");
                String dateTime = dateFormat.format(date) + " 13:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_13")) {
                logger.info("Done_13!");
                String dateTime = dateFormat.format(date) + " 13:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_14")){
                logger.info("Progress_14!");
                String dateTime = dateFormat.format(date) + " 14:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_14")) {
                logger.info("Done_14!");
                String dateTime = dateFormat.format(date) + " 14:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_15")){
                logger.info("Progress_15!");
                String dateTime = dateFormat.format(date) + " 15:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_15")) {
                logger.info("Done_15!");
                String dateTime = dateFormat.format(date) + " 15:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_16")){
                logger.info("Progress_16!");
                String dateTime = dateFormat.format(date) + " 16:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_16")) {
                logger.info("Done_16!");
                String dateTime = dateFormat.format(date) + " 16:00";
                pressedDoneButton(dateTime);
            }
            if (function.equals("In Progress_17")){
                logger.info("Progress_17!");
                String dateTime = dateFormat.format(date) + " 17:00";
                pressedInProgressButton(dateTime);
            }
            if (function.equals("Done_17")) {
                logger.info("Done_17!");
                String dateTime = dateFormat.format(date) + " 17:00";
                pressedDoneButton(dateTime);
            }
        redrawAdminPage(modelAndView);
        return modelAndView;
    }

    public void pressedInProgressButton(String dateTime) {
        Optional<Service> serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            serviceOpt.get().setStatus("In Progress");
            serviceRepo.save(serviceOpt.get());
        }
    }

    public void pressedDoneButton(String dateTime) {
        Optional<Service> serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            serviceOpt.get().setStatus("Done");
            serviceRepo.save(serviceOpt.get());

            StringBuilder sb = new StringBuilder();
            sb.append("Dear Customer!<br/> \n");
            sb.append("<br/>\n");
            sb.append("The work with your car is done!<br/>\n");
            sb.append("<br/>\n");
            sb.append("Best regards,<br/>\n");
            sb.append("Your AutoServiceA&A Team<br/>\n");
            SendEmail.SendMailMessage(sb, serviceOpt.get().getEmail());
        }
    }

    public void redrawAdminPage(ModelAndView modelAndView) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        logger.info(dateFormat.format(date));
        modelAndView.addObject("jumbo_time", dateFormat.format(date));

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = new Date();
        String dateTime = dateFormat.format(date) + " 10:00";
        Optional<Service> serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo11", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo12", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo13", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo14", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo11", "FREE");
            modelAndView.addObject("jumbo12", "");
            modelAndView.addObject("jumbo13", "");
            modelAndView.addObject("jumbo14", "");
        }

        dateTime = dateFormat.format(date) + " 11:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo21", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo22", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo23", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo24", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo21", "FREE");
            modelAndView.addObject("jumbo22", "");
            modelAndView.addObject("jumbo23", "");
            modelAndView.addObject("jumbo24", "");
        }

        dateTime = dateFormat.format(date) + " 12:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo31", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo32", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo33", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo34", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo31", "FREE");
            modelAndView.addObject("jumbo32", "");
            modelAndView.addObject("jumbo33", "");
            modelAndView.addObject("jumbo34", "");
        }

        dateTime = dateFormat.format(date) + " 13:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo41", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo42", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo43", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo44", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo41", "FREE");
            modelAndView.addObject("jumbo42", "");
            modelAndView.addObject("jumbo43", "");
            modelAndView.addObject("jumbo44", "");
        }

        dateTime = dateFormat.format(date) + " 14:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo51", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo52", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo53", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo54", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo51", "FREE");
            modelAndView.addObject("jumbo52", "");
            modelAndView.addObject("jumbo53", "");
            modelAndView.addObject("jumbo54", "");
        }

        dateTime = dateFormat.format(date) + " 15:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo61", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo62", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo63", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo64", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo61", "FREE");
            modelAndView.addObject("jumbo62", "");
            modelAndView.addObject("jumbo63", "");
            modelAndView.addObject("jumbo64", "");
        }

        dateTime = dateFormat.format(date) + " 16:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo71", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo72", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo73", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo74", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo71", "FREE");
            modelAndView.addObject("jumbo72", "");
            modelAndView.addObject("jumbo73", "");
            modelAndView.addObject("jumbo74", "");
        }

        dateTime = dateFormat.format(date) + " 17:00";
        serviceOpt= serviceRepo.findByDateTime(dateTime);
        if (serviceOpt.isPresent()) {
            modelAndView.addObject("jumbo81", "Model: " + serviceOpt.get().getModel());
            modelAndView.addObject("jumbo82", "Service: " + serviceOpt.get().getWork());
            Optional<User> userOpt = userRepo.findByEmail(serviceOpt.get().getEmail());
            modelAndView.addObject("jumbo83", "Phone: " + userOpt.get().getTelephoneNumber());
            modelAndView.addObject("jumbo84", "Status: " + serviceOpt.get().getStatus());
        }
        else {
            modelAndView.addObject("jumbo81", "FREE");
            modelAndView.addObject("jumbo82", "");
            modelAndView.addObject("jumbo83", "");
            modelAndView.addObject("jumbo84", "");
        }
    }
}

