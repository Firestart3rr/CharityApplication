package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserServiceImpl;

import java.util.List;

@AllArgsConstructor
@Controller
@Secured("ROLE_USER")
@RequestMapping("/userIndex")
public class UserController {

    private static final String RETURN_USER_INDEX_PAGE = "userIndex";
    private static final String RETURN_USER_PROFILE = "user/userProfile";

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

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

    @GetMapping("/profile/{email}")
    public String showUserData(Model model, @PathVariable String email){
        model.addAttribute("appUser", userRepository.findAppUserByEmail(email));
        return RETURN_USER_PROFILE;
    }


}
