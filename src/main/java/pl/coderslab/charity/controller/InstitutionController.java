package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@AllArgsConstructor
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/institution")
public class InstitutionController {

    private static final String RETURN_INSTITUTION_LIST = "institutions";

    private final InstitutionRepository institutionRepository;

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @GetMapping("/list")
    public String showInstitutions(Model model){
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return RETURN_INSTITUTION_LIST;
    }
}
