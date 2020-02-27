package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/userIndex")
public class UserController {

    private static final String RETURN_USER_INDEX_PAGE = "userIndex";

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

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
//        CurrentUser currentUser = new CurrentUser();
        AppUser appUser = new AppUser();
        model.addAttribute("sumOfDonatedBags", donationRepository.sumOfAllDonatedBags());
//        model.addAttribute("appUser", appUser);
        return RETURN_USER_INDEX_PAGE;
    }


}
