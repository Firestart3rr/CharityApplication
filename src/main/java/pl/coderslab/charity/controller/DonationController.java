package pl.coderslab.charity.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.DonationServiceImpl;
import pl.coderslab.charity.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/donation")
public class DonationController {

    private static final String RETURN_DONATION_FORM = "form";
    private static final String RETURN_USER_DONATION_LIST = "donation/donations";
    private static final String RETURN_DONATION_FORM_CONFIRMATION = "formConfirmation";
    private static final String REDIRECT_TO_CONFIRMATION_FORM = "redirect:/donation/form/confirmation";
    private static final String REDIRECT_TO_LIST_OF_DONATIONS = "redirect:/donation/list";

    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private UserServiceImpl userService;
    private DonationServiceImpl donationService;

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        Donation donation = new Donation();
        model.addAttribute("donation", donation);

        return RETURN_DONATION_FORM;
    }

    @PostMapping("/form")
    public String saveForm(Donation donation) {
//        donationRepository.saveDonation(donation.getCity(), donation.getPickUpComment(), donation.getPickUpDate(), donation.getPickUpTime(),
//                donation.getQuantity(), donation.getStreet(), donation.getZipCode(), donation.getInstitution(), userService.getUserFromContext(), donation.isPickedUp(), donation.getCreateDate());
        donation.setAppUser(userService.getUserFromContext());
        donation.setCreateDate(LocalDateTime.now());
        donationRepository.save(donation);
        return REDIRECT_TO_CONFIRMATION_FORM;
    }

    @GetMapping("/form/confirmation")
    public String showConfirmation() {
        return RETURN_DONATION_FORM_CONFIRMATION;
    }

    @GetMapping("/list")
    public String showUserDonations() {
        return RETURN_USER_DONATION_LIST;
    }

    @GetMapping("/pickUp/{id}")
    public String checkAsPickedUp(@PathVariable Integer id){
        donationService.checkDonationAsPickedUp(id);
        return REDIRECT_TO_LIST_OF_DONATIONS;
    }
}
