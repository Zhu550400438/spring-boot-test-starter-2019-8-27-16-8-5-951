package com.oocl.web.sampleWebApp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello World";
    }
    
    
    @RequestMapping("/hello")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String returnBadRequest() {
        return "Hello World";
    }
    
    @RequestMapping("/map")
    public @ResponseBody Map<String, String> getMap() {
     Map<String, String> map = new HashMap<String, String>();
     map.put("user", "name");
     return map;
  
}
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Map<String, String> getPostMap(@RequestBody Map<String, String> mapRequest) {
     Map<String, String> map = mapRequest;
     return map;
    }
}