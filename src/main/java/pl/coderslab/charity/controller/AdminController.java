package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {

    private static final String RETURN_ADMIN_LIST = "admin/admins";
    private static final String RETURN_ADMIN_FORM = "admin/adminAdd";
    private static final String RETURN_ADMIN_INDEX_PAGE = "admin/adminIndex";
    private static final String REDIRECT_TO_ADMIN_LIST = "redirect:/admin/list";
    private static final String REDIRECT_TO_ADMIN_INDEX_PAGE = "redirect:/admin";

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @ModelAttribute("admins")
    public List<AppUser> getAllAdmins() {
        return userRepository.selectAdmins();
    }

    @GetMapping("")
    public String showAdminIndexPage(Model model) {
        model.addAttribute("sumOfDonatedBags", donationRepository.sumOfAllDonatedBags());
        return RETURN_ADMIN_INDEX_PAGE;
    }

    @GetMapping("/list")
    public String getListOfAdmins() {
        return RETURN_ADMIN_LIST;
    }

    @GetMapping("/add")
    public String getAdminForm(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        return RETURN_ADMIN_FORM;
    }

    @PostMapping("/add")
    public String saveAdmin(@Valid AppUser appUser, BindingResult result) {
        if (result.hasErrors()) {
            return RETURN_ADMIN_FORM;
        }
        userService.saveAdmin(appUser);
        return REDIRECT_TO_ADMIN_INDEX_PAGE;
    }

    @GetMapping("/edit/{id}")
    public String editAdmin(Model model, @PathVariable Integer id) {
        model.addAttribute("appUser", userRepository.findById(id));
        return RETURN_ADMIN_FORM;
    }

    @PostMapping("/edit/{id}")
    public String editAdmin(@Valid AppUser appUser, BindingResult result) {
        if (result.hasErrors()) {
            return RETURN_ADMIN_FORM;
        }
        userService.saveAdmin(appUser);
        return REDIRECT_TO_ADMIN_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@ModelAttribute AppUser appUser, @ModelAttribute Role role) {
        userRepository.delete(appUser);
        return REDIRECT_TO_ADMIN_LIST;
    }
}
