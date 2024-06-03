package ga.esiitech.schoolapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class Hello {

    @GetMapping
    public String hello() {
        return "welcome";
    }
 }
