package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.UserMessage;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    @ModelAttribute("userMessage")
    public UserMessage prepareUserMessage() { return new UserMessage(); }

    @ModelAttribute("uiContext")
    public String setUiContext() { return "Donation"; }

    @ModelAttribute("donation")
    public Donation prepareDonation() { return new Donation(); }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        categories.sort(Comparator.comparing(Category::getSequence));
        return categories;
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        List<Institution> institutions = institutionRepository.findAll();
        institutions.sort(Comparator.comparing(Institution::getSequence));
        return institutions;
    }

    @GetMapping("/donation")
    public String displayDonationForm() { return "form"; }

    @PostMapping("/donation")
    public String processDonationForm(@Valid @ModelAttribute Donation donation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            return "form";
        }
        donationRepository.save(donation);
        return "redirect:/formConfirmation";
    }
}