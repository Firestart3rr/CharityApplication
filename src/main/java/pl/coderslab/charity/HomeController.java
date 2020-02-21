package pl.coderslab.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {

    private static final String RETURN_LANDING_PAGE = "index";

    private DonationRepository donationRepository;
    private InstitutionRepository institutionRepository;

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @RequestMapping({"/", ""})
    public String homeAction(Model model) {
        model.addAttribute("sumOfDonatedBags", donationRepository.sumOfAllDonatedBags());
        return RETURN_LANDING_PAGE;
    }
}
