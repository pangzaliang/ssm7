package z.z.controller.fuck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/fuck")
@Controller
public class FuckController {

    @GetMapping("/hi")
    public Map<String, Object> hi () {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "fuck ");
        return hashMap;
    }

    @GetMapping("/hi2")
    public ModelAndView hi2 () {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "fuck ");
        return new ModelAndView(new MappingJackson2JsonView(), hashMap);
    }

    @GetMapping("/hi3")
    public Map<String, Object> hi3 () {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "fuck ");
        return hashMap;
    }
}
