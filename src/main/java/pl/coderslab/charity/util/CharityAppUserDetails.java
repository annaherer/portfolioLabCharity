package pl.coderslab.charity.util;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.coderslab.charity.dto.UserAccountDTO;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CharityAppUserDetails implements UserDetails {
    private final UserAccountDTO userAccountDTO;

    public CharityAppUserDetails(UserAccountDTO userAccountDTO) {
        this.userAccountDTO = userAccountDTO;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (userAccountDTO == null) {
            return authorities;
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (userAccountDTO.getAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return userAccountDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userAccountDTO.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return userAccountDTO.getEnabled();
    }
}