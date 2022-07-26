package by.mifort.automation.hr.dev.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testcontroller")
public class HealthCheckController {

    @GetMapping
    public String vo() {
        return "CI is WORKING123!";
    }

    @GetMapping("/static")
    public String staticUrl() {
        return "STATIC IP IS WORKING";
    }
}
