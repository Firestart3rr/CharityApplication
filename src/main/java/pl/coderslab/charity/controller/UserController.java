package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.UserServiceImpl;

@AllArgsConstructor
@Controller
@RequestMapping("/profile")
public class UserController {

    private UserServiceImpl userService;



}
