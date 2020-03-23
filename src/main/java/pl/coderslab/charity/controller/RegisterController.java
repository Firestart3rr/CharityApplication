package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.registration.OnRegistrationCompleteEvent;
import pl.coderslab.charity.service.EmailServiceImpl;
import pl.coderslab.charity.service.UserServiceImpl;
import pl.coderslab.charity.service.VerificationTokenService;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final String RETURN_REGISTER_FORM = "register";
    private static final String REDIRECT_TO_LANDING_PAGE = "redirect:/";
    private static final String REDIRECT_TO_LOGIN_PAGE = "redirect:/login";

    private UserServiceImpl userService;
    private ApplicationEventPublisher eventPublisher;
    private VerificationTokenService verificationTokenService;

    private final String ROLE_USER = "ROLE_USER";

    @GetMapping("")
    public String showRegistrationForm(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        return RETURN_REGISTER_FORM;
    }

    @PostMapping("")
    public String registerNewUser(@Valid AppUser appUser, BindingResult result, WebRequest request) {
        if (result.hasErrors()) {
            return RETURN_REGISTER_FORM;
        }
        userService.saveUser(appUser, ROLE_USER);
        AppUser registered = userService.findUserByEmail(appUser.getEmail());

        String appURL = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appURL,
                request.getLocale(), registered));

        return REDIRECT_TO_LANDING_PAGE;
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        AppUser appUser = verificationToken.getAppUser();
        appUser.setEnabled(true);
        userService.saveRegisteredUser(appUser);
        verificationTokenService.removeTokenFromDataBase(verificationToken);
        return REDIRECT_TO_LOGIN_PAGE;
    }
}
