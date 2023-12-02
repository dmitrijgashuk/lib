package com.example.intellias.lib.security;

import com.example.intellias.lib.domain.CustomUser;
import com.example.intellias.lib.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    @Autowired
    public UserDetailsServiceImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.getSecurityUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return SecurityUser.fromCustomUser(customUser);
    }
}
