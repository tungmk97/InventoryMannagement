package inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewController {
    @RequestMapping(value = {"/", "index"})
    public String hello() {
        return "index";
    }
}
