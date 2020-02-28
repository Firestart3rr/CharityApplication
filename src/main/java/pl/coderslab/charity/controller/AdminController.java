package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {

    private static final String RETURN_ADMIN_LIST = "admin/admins";
    private static final String RETURN_ADMIN_INDEX_PAGE = "admin/adminIndex";

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @ModelAttribute("admins")
    public List<AppUser> getAllAdmins(){
        return userRepository.selectAdmins();
    }

    @GetMapping("")
    public String showAdminIndexPage(Model model) {
        model.addAttribute("sumOfDonatedBags", donationRepository.sumOfAllDonatedBags());
        return RETURN_ADMIN_INDEX_PAGE;
    }

    @GetMapping("/list")
    public String getListOfAdmins(){
        return RETURN_ADMIN_LIST;
    }
}
