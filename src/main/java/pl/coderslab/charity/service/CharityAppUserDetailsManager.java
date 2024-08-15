package pl.coderslab.charity.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.UserAccountDTO;
import pl.coderslab.charity.model.UserAccount;
import pl.coderslab.charity.repository.UserAccountRepository;
import pl.coderslab.charity.util.CharityAppUserDetails;
import java.util.Collection;

@Service
public class CharityAppUserDetailsManager implements UserDetailsManager {
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;

    public CharityAppUserDetailsManager(UserAccountRepository userAccountRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;

        if (this.userCount() == 0) {
            UserAccountDTO admin = new UserAccountDTO();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setEmail("admin@charityapp.com");
            admin.setEnabled(true);
            admin.setAdmin(true);

            this.createUser(new CharityAppUserDetails(admin));
        }
    }

    @Override
    public void createUser(UserDetails user) {
        UserAccount userAccount = modelMapper.map(((CharityAppUserDetails) user).getUserAccountDTO(), UserAccount.class);
        userAccount.setId(null);
        userAccountRepository.save(userAccount);
        ((CharityAppUserDetails) user).getUserAccountDTO().setId(userAccount.getId());
    }

    @Override
    public void updateUser(UserDetails user) {
        UserAccount userAccount = modelMapper.map(((CharityAppUserDetails) user).getUserAccountDTO(), UserAccount.class);
        if (userAccount.getId() != null)
            userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUser(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        if (userAccount != null)
            userAccountRepository.delete(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserAccount userAccount = userAccountRepository.findByUsername(username);
            if (userAccount != null) {
                userAccount.setPassword(newPassword);
                userAccountRepository.save(userAccount);
            }
        }
    }

    @Override
    public boolean userExists(String username) {
        return (userAccountRepository.findByUsername(username) != null);
    }

    @Override
    public CharityAppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        if (userAccount != null)
            return userAccountToUserDetails(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    public boolean userEmailExists(String email) {
        return (userAccountRepository.findByEmail(email) != null);
    }

    public CharityAppUserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);

        if (userAccount != null)
            return userAccountToUserDetails(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    public CharityAppUserDetails loadUserById(Long id) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findById(id).orElse(null);

        if (userAccount != null)
            return userAccountToUserDetails(userAccount);
        else
            throw new UsernameNotFoundException("User not found");
    }

    public Collection<CharityAppUserDetails> loadAllUsers() {
        return userAccountRepository.findAll().stream().map(this::userAccountToUserDetails).toList();
    }

    public long userCount() {
        return userAccountRepository.count();
    }

    private CharityAppUserDetails userAccountToUserDetails(UserAccount userAccount) {
        return new CharityAppUserDetails(modelMapper.map(userAccount, UserAccountDTO.class));
    }
}