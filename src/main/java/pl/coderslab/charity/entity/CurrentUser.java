package pl.coderslab.charity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class CurrentUser extends User {

    private final AppUser appUser;

    public CurrentUser(String email, String password, boolean enabled,
                       boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities,
                       AppUser appUser) {
        super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
