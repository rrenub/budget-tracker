package dev.rdelgado.budgettracker.api.budgetTrackerAPI.security;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.User;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories.UserRepository;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found exception"));

        return UserDetailsImpl.build(user);
    }
}
