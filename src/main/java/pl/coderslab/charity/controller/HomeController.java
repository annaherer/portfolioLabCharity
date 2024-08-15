package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.dto.MessageDTO;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
@AllArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    @ModelAttribute("messageDTO")
    public MessageDTO getMessageDTO() {
        return new MessageDTO();
    }

    @GetMapping("/")
    public String homeAction(Model model) {
        List<Institution> allInst = institutionService.findAll();
        List<Institution> institutions1 = allInst.subList(0, allInst.size() / 2);
        List<Institution> institutions2 = allInst.subList(allInst.size() / 2, allInst.size());

        model.addAttribute("institutions_1", institutions1);
        model.addAttribute("institutions_2", institutions2);

        model.addAttribute("all_bags", donationService.countAllBags());
        model.addAttribute("all_donations", donationService.countAllDonations());
        return "index";
    }

    @GetMapping("/messageConfirmation")
    public String messageConfirmation() {
        return "message-confirmation";
    }

    @PostMapping("/submitMessage")
    public String submitMessage(@Valid @ModelAttribute MessageDTO messageDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        return "redirect:/messageConfirmation";
    }
}