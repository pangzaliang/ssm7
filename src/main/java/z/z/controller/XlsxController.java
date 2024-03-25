package z.z.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import z.z.view.PDFView;
import z.z.view.XlsxView;

import java.util.HashMap;
import java.util.Map;


/**
 * XLSX表格简单示例
 */
@Controller
public class XlsxController {

    @GetMapping("/xlsx")
    public ModelAndView xlsx () {
        Map<String,String> userData = new HashMap<>();
        userData.put("1", "Mahesh");
        userData.put("2", "Suresh");
        userData.put("3", "Ramesh");
        userData.put("4", "Naresh");
        return new ModelAndView(new XlsxView(),null);
    }
}
