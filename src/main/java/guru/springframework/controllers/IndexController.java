package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    Tells Spring that this is a Bean
 */
@Controller
public class IndexController {

    /*
        @RequestMapping({"","/","/index"}) is the URL path to the index.html page
       return "index" name must match the actual htm file name
     */
    @RequestMapping({"","/","/index"})
    public String getIndexPage(){
        return "index";
    }
}
