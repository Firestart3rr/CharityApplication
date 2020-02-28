package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/institution")
public class InstitutionController {

    private static final String RETURN_INSTITUTION_LIST = "institution/institutions";
    private static final String RETURN_INSTITUTION_FORM = "institution/institutionAdd";
    private static final String REDIRECT_TO_INSTITUTION_FORM = "redirect:/institution/add";
    private static final String REDIRECT_TO_INSTITUTION_LIST = "redirect:/institution/list";

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @GetMapping("/list")
    public String showInstitutions() {
        return RETURN_INSTITUTION_LIST;
    }

    @GetMapping("/add")
    public String addInstitution(Model model) {
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return RETURN_INSTITUTION_FORM;
    }

    @PostMapping("/add")
    public String addInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return RETURN_INSTITUTION_FORM;
        }
        institutionRepository.save(institution);
        return REDIRECT_TO_INSTITUTION_FORM;
    }

    @GetMapping("/edit/{id}")
    public String editInstitution(Model model, @PathVariable Integer id) {
        model.addAttribute("institution", institutionRepository.findById(id));
        return RETURN_INSTITUTION_FORM;
    }

    @PostMapping("/edit/{id}")
    public String editInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return RETURN_INSTITUTION_FORM;
        }
        institutionRepository.save(institution);
        return REDIRECT_TO_INSTITUTION_LIST;
    }

    @GetMapping("/delete/{id}")
    public String delInstitution(@ModelAttribute Institution institution, @ModelAttribute Donation donation) {
        if (donation.getInstitution() != institution) {
            donationRepository.detachDonationWithInstitutionFromInstitutions(institution.getId());
            institutionRepository.delete(institution);
        } else {
            institutionRepository.delete(institution);
        }
        return REDIRECT_TO_INSTITUTION_LIST;
    }
}
