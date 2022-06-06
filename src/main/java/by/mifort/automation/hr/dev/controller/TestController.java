package by.mifort.automation.hr.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testcontroller")
public class TestController {

    @GetMapping
    public String vo(){
        return "CI is WORKING123!";
    }
}
