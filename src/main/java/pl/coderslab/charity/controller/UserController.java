package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.error.InvalidOldPasswordException;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Controller
@Secured("ROLE_USER")
@RequestMapping("/userIndex")
public class UserController {

    private static final String RETURN_USER_INDEX_PAGE = "userIndex";
    private static final String RETURN_USER_PROFILE = "user/userProfile";
    private static final String RETURN_USER_EDIT_FORM = "user/userEdit";
    private static final String REDIRECT_TO_USER_INDEX_PAGE = "redirect:/userIndex";

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    private final String ROLE_USER = "ROLE_USER";
    private final String ROLE_ADMIN = "ROLE_ADMIN";

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @GetMapping("")
    public String showUserIndexPage(Model model) {
        model.addAttribute("sumOfDonatedBags", donationRepository.sumOfAllDonatedBags());
        return RETURN_USER_INDEX_PAGE;
    }

    @GetMapping("/profile/{id}")
    public String showUserData(Model model) {
        model.addAttribute("appUser", userRepository.findById(userService.getUserFromContext().getId()));
        return RETURN_USER_PROFILE;
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model) {
        model.addAttribute("appUser", userRepository.findById(userService.getUserFromContext().getId()));
        return RETURN_USER_EDIT_FORM;
    }

    @PostMapping("/edit/{id}")
    public String editUser(@Valid AppUser appUser, BindingResult result, Locale locale) {
        if (result.hasErrors()) { 
            return RETURN_USER_EDIT_FORM;
        }
        if (!userService.checkIfValidOldPassword(appUser)) {
            throw new InvalidOldPasswordException();
        }
        userService.saveUser(appUser, ROLE_USER);
        return REDIRECT_TO_USER_INDEX_PAGE;
    }


}
