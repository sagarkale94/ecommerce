package co.in.sagarkale.ecommerce.components;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal extends User implements UserDetails {

    private Long id; // Store user ID here

    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;  // Initialize user ID
    }

    // Getter method for user ID
    public Long getId() {
        return id;
    }
}

