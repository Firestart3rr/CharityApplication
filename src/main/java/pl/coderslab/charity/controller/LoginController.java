package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static final String RETURN_LOGIN_FORM = "login";

    @GetMapping("/login")
    public String login() {
        return RETURN_LOGIN_FORM;
    }

}
