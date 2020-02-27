package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.service.UserServiceImpl;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final String RETURN_REGISTER_FORM = "register";
    private static final String REDIRECT_TO_LANDING_PAGE = "redirect:/";

    private UserServiceImpl userService;

    @GetMapping("")
    public String showRegistrationForm(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        return RETURN_REGISTER_FORM;
    }

    @PostMapping("")
    public String addNewUserToDB(AppUser appUser) {
        userService.saveUser(appUser);
        return REDIRECT_TO_LANDING_PAGE;
    }
}
