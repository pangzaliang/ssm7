package z.z.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/hi")
@RestController
public class HiController {

    @GetMapping("/hi")
    public Map<String, Object> hi () {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "fuck ");
        return hashMap;
    }

    @GetMapping("/hi2")
    public Map<String, Object> hi2 () {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "fuck ");

        int i = 1 / 0;
        return hashMap;
    }

    @GetMapping("/hi3")
    public Map<String, Object> hi3 () {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "fuck ");
        return hashMap;
    }
}
