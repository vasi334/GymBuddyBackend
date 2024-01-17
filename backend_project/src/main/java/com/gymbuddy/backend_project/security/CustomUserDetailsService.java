package com.gymbuddy.backend_project.security;

import com.gymbuddy.backend_project.entity.Role;
import com.gymbuddy.backend_project.entity.User;
import com.gymbuddy.backend_project.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    // Constructor to inject UserRepository
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Implementation of the UserDetailsService interface method
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Retrieve user from the repository by email
        User user = userRepository.findByEmail(email);

        // Check if the user exists
        if (user != null) {
            // Return a UserDetails object with username, password, and authorities (roles)
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } else {
            // Throw an exception if the user is not found
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    // Helper method to map roles to authorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        // Map roles to SimpleGrantedAuthority and collect into a list
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
