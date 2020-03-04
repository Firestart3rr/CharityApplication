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
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
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
    private static final String RETURN_USER_LIST = "user/users";
    private static final String REDIRECT_TO_USER_LIST = "redirect:/admin/user/list";

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

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

    @ModelAttribute("admins")
    public List<AppUser> getAllAdmins() {
        return userRepository.selectAdmins();
    }

    @ModelAttribute("users")
    public List<AppUser> getAllUsers(){
        return userRepository.selectUsers();
    }

    @GetMapping("")
    public String showAdminIndexPage(Model model) {
        model.addAttribute("sumOfDonatedBags", donationRepository.sumOfAllDonatedBags());
        return RETURN_ADMIN_INDEX_PAGE;
    }

    @GetMapping("/list")
    public String showAllAdmins() {
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
        userService.saveUser(appUser, ROLE_ADMIN);
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
        userService.saveUser(appUser, ROLE_ADMIN);
        return REDIRECT_TO_ADMIN_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@ModelAttribute AppUser appUser, @ModelAttribute Role role) {
        userRepository.delete(appUser);
        return REDIRECT_TO_ADMIN_LIST;
    }

    @GetMapping("/user/list")
    public String showAllUsers() {
        return RETURN_USER_LIST;
    }

    @GetMapping("/user/changeStatus/{id}")
    public String changeUserStatus(@PathVariable Integer id){
        AppUser appUser = userRepository.findById(id).get();
        if(appUser.isEnabled()){
            userRepository.blockUser(id);
        } else if (!appUser.isEnabled()){
            userRepository.unblockUser(id);
        }
        return RETURN_USER_LIST;
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@ModelAttribute AppUser appUser){
        userRepository.delete(appUser);
        return REDIRECT_TO_USER_LIST;
    }

}
