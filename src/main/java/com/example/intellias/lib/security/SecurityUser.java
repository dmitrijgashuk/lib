package com.example.intellias.lib.security;

import com.example.intellias.lib.domain.CustomUser;
import com.example.intellias.lib.domain.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    private final String login;
    public final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String login, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.login = login;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails  fromCustomUser(CustomUser customUser){
        return new User(customUser.getLogin(),
                customUser.getPassword(),
                customUser.getStatus().equals(Status.ACTIVE),
                customUser.getStatus().equals(Status.ACTIVE),
                customUser.getStatus().equals(Status.ACTIVE),
                customUser.getStatus().equals(Status.ACTIVE),
                customUser.getRole().getAuthority());
    }

}
