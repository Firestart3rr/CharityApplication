package pl.coderslab.charity.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/donation")
public class DonationController {

    private static final String RETURN_DONATION_FORM = "form";
    private static final String RETURN_DONATION_FORM_CONFIRMATION = "formConfirmation";
    private static final String REDIRECT_TO_CONFIRMATION_FORM = "redirect:/donation/form/confirmation";

    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

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
        donationRepository.saveDonation(donation.getCity(), donation.getPickUpComment(), donation.getPickUpDate(), donation.getPickUpTime(),
                donation.getQuantity(), donation.getStreet(), donation.getZipCode(), donation.getInstitution());
        return REDIRECT_TO_CONFIRMATION_FORM;
    }

    @GetMapping("/form/confirmation")
    public String showConfirmation() {
        return RETURN_DONATION_FORM_CONFIRMATION;
    }
}
