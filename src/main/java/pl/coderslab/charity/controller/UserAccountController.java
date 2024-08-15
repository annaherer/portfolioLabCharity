package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.dto.ChangePasswordDTO;
import pl.coderslab.charity.util.CharityAppUserDetails;
import pl.coderslab.charity.service.CharityAppUserDetailsManager;
import pl.coderslab.charity.dto.UserAccountReducedDTO;

@Controller
public class UserAccountController {
    private final CharityAppUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    public UserAccountController(CharityAppUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("userDetails")
    public CharityAppUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return userDetailsManager.loadUserByUsername(authentication.getName());
        else
            return null;
    }

    @GetMapping("/accountProfile")
    public String accountProfile(Model model, @ModelAttribute("userDetails") CharityAppUserDetails userDetails) {
        UserAccountReducedDTO userAccountReducedDTO = userDetails.getUserAccountDTO();
        model.addAttribute("userAccountReducedDTO", userAccountReducedDTO);
        return "accountProfile";
    }

    @PostMapping("/accountProfile")
    public String accountProfileSave(@ModelAttribute("userDetails") CharityAppUserDetails userDetails, @Valid @ModelAttribute UserAccountReducedDTO userAccountReducedDTO, BindingResult result) {
        userAccountReducedDTO.normalizeStringAttributes();

        if (!userDetails.getUserAccountDTO().getEmail().equals(userAccountReducedDTO.getEmail()) &&
                userDetailsManager.userEmailExists(userAccountReducedDTO.getEmail()))
            result.addError(new FieldError("userAccountReducedDTO", "email", "Email already exists"));

        if (result.hasErrors()) {
            return "accountProfile";
        }

        userDetails.getUserAccountDTO().setEmail(userAccountReducedDTO.getEmail());
        userDetails.getUserAccountDTO().setFirstName(userAccountReducedDTO.getFirstName());
        userDetails.getUserAccountDTO().setLastName(userAccountReducedDTO.getLastName());
        userDetailsManager.updateUser(userDetails);

        Authentication authentication = new PreAuthenticatedAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePasswordPost(@ModelAttribute ChangePasswordDTO changePasswordDTO, @ModelAttribute("userDetails") CharityAppUserDetails userDetails, RedirectAttributes redirectAttributes) {
        String newPassword = changePasswordDTO.getNewPassword();

        if (!newPassword.equals(changePasswordDTO.getNewPasswordRetyped()))
            redirectAttributes.addAttribute("errorMessage", "Retyped password does not match");
        else if (newPassword.isEmpty())
            redirectAttributes.addAttribute("errorMessage", "Password can't be blank");
        else {
            String encodedPassword = passwordEncoder.encode(newPassword);
            userDetailsManager.changePassword("", encodedPassword);
            Authentication authentication = new PreAuthenticatedAuthenticationToken(userDetails, encodedPassword, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            redirectAttributes.addAttribute("confirmMessage", "Password changed successfully");
        }

        return "redirect:/accountProfile";
    }
}