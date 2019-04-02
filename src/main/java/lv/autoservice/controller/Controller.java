package lv.autoservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}

