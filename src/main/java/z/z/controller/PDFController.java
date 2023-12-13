package z.z.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller

public class PDFController {


    @GetMapping("/pdf")
    public ModelAndView pdf () {
        //user data
        Map<String,String> userData = new HashMap<>();
        userData.put("1", "Mahesh");
        userData.put("2", "Suresh");
        userData.put("3", "Ramesh");
        userData.put("4", "Naresh");
        return new ModelAndView("PDFView","userData",userData);

    }
}
