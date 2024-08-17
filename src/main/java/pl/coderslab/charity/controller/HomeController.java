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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


@Controller
@AllArgsConstructor
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserMessageRepository userMessageRepository;

    @ModelAttribute("userMessage")
    public UserMessage prepareUserMessage() {
        return new UserMessage();
    }

    @ModelAttribute("uiContext")
    public String setUiContext() {
        return "Home";
    }

    @GetMapping({"/home", "/"})
    public String homeAction(Model model) {
        List<Institution> sampleInst = institutionRepository.findAll();
        Random rand = new Random();
        sampleInst.sort(Comparator.comparing(f -> rand.nextInt()));

        model.addAttribute("sampleInstitutions", sampleInst);
        model.addAttribute("all_bags", donationRepository.countAllBags());
        model.addAttribute("all_donations", donationRepository.countAllDonations());
        return "index";
    }

    @GetMapping("/messageConfirmation")
    public String messageConfirmation() {
        return "message-confirmation";
    }

    @GetMapping("/formConfirmation")
    public String formConfirmation() {
        return "form-confirmation";
    }

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