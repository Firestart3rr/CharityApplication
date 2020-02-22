package pl.coderslab.charity.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/donation")
public class DonationController {

    private static final String SHOW_DONATION_FORM = "form";

    private CategoryRepository categoryRepository;

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/form")
    public String showForm(){
        return SHOW_DONATION_FORM;
    }

}
