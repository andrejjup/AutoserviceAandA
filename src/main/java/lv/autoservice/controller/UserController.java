package lv.autoservice.controller;

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

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private ServiceRepository serviceRepo;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jumbo", "");
 //       modelAndView.addObject("jumbo2", "1");
   //     modelAndView.addObject("jumbo3", "1");
//        modelAndView.addObject("jumbo4", "2");
//        modelAndView.addObject("jumbo5", "2");
        modelAndView.setViewName("user");
//        String userName= String.valueOf(modelAndView.getModelMap());
//        Optional<Service> serviceOpt = serviceRepo.findByEmail(userName);
//                modelAndView.addObject("jumbo2", serviceOpt.get().getModel());
//                modelAndView.addObject("jumbo3", serviceOpt.get().getWork());
//                modelAndView.addObject("jumbo4", serviceOpt.get().getDateTime());
//                modelAndView.addObject("jumbo5", "");

        return modelAndView;
    }
}
