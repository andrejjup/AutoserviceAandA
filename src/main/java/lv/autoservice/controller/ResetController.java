package lv.autoservice.controller;


import lv.autoservice.businesslogic.builder.User;
import lv.autoservice.businesslogic.database.UserRepository;
import lv.autoservice.businesslogic.email.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Random;

@Controller
public class ResetController {

    @Autowired
    private UserRepository userRepo;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/reset")
    public ModelAndView reset(String email) {
        ModelAndView modelAndView = new ModelAndView();
        if (email== null) {
            modelAndView.addObject("jumbo", "");
        }
        else {
            Optional<User> userOpt = userRepo.findByEmail(email);
            if (!userOpt.isPresent()) {
                modelAndView = errorMsg(modelAndView);
            }
            else {
                Random randomGenerator = new Random();
                String newPassword= "";
                int[] randomArray= new int[6];

                for (int i= 0; i< 6; i++) {
                    randomArray[i] = randomGenerator.nextInt(10);
                    newPassword= newPassword + randomArray[i];
                }

                StringBuilder sb = new StringBuilder();
                sb.append("Dear Customer!<br/> \n");
                sb.append("<br/>\n");
                sb.append("Your new password: " + newPassword);
                sb.append("<br/>\n");
                sb.append("<br/>\n");
                sb.append("Best regards,<br/>\n");
                sb.append("Your AutoServiceA&A Team<br/>\n");
                    if (SendEmail.SendMailMessage(sb, email)) {
                        logger.info("New password was send on your email!");

                        userOpt.get().setPassword(newPassword);
                        userRepo.save(userOpt.get());
                    }
                modelAndView.setViewName("home");
                modelAndView.addObject("jumbo1", "");
                modelAndView.addObject("jumbo2", "");
                modelAndView.addObject("jumbo3", "");
                modelAndView.addObject("jumbo4", "");
                modelAndView.addObject("jumbo5", "");
                modelAndView.addObject("jumbo6", "");
                modelAndView.addObject("jumbo7", "");
            }
        }
        return modelAndView;
    }


    public ModelAndView errorMsg(ModelAndView modelAndView) {
        modelAndView.addObject("jumbo", "You are not registered!");
        return modelAndView;
    }
}



