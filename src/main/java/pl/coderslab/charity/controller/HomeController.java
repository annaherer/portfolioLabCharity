package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.UserMessage;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserMessageRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


@Controller
@AllArgsConstructor
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserMessageRepository userMessageRepository;

    // User message object for user message form
    @ModelAttribute("userMessage")
    public UserMessage prepareUserMessage() {
        return new UserMessage();
    }

    // UI context for view - dashboard or form
    @ModelAttribute("uiContext")
    public String setUiContext() {
        return "Home";
    }

    // Number of bags, number of donations, random listing of 4 institutions
    @GetMapping({"/home", "/"})
    public String homeAction(Model model) {
        model.addAttribute("all_bags", donationRepository.countAllBags());
        model.addAttribute("all_donations", donationRepository.countAllDonations());

        List<Institution> sampleInstitutions = institutionRepository.findAll();
        Random rand = new Random();
        sampleInstitutions.sort(Comparator.comparing(institution -> rand.nextInt()));
        model.addAttribute("sampleInstitutions", sampleInstitutions.subList(0, Math.min(sampleInstitutions.size(), 4)));

        return "index";
    }

    // Displays message confirmation
    @GetMapping("/messageConfirmation")
    public String messageConfirmation() {
        return "message-confirmation";
    }

    // Saves message to database or displays errors on the home page
    @PostMapping("/submitMessage")
    public String submitMessage(@Valid @ModelAttribute UserMessage userMessage, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        userMessage.setCreatedAt(LocalDateTime.now());
        userMessageRepository.save(userMessage);
        return "redirect:/messageConfirmation";
    }
}