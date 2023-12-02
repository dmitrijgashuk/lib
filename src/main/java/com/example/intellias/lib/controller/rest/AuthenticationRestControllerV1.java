package com.example.intellias.lib.controller.rest;

import com.example.intellias.lib.config.JwtTokenProvider;
import com.example.intellias.lib.domain.CustomUser;
import com.example.intellias.lib.dto.AuthenticationRequestDto;
import com.example.intellias.lib.repository.CustomUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final CustomUserRepository customUserRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, CustomUserRepository customUserRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.customUserRepository = customUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword()));
            CustomUser customUser = customUserRepository.getSecurityUserByLogin(request.getLogin()).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
            String token = jwtTokenProvider.createToken(request.getLogin(), customUser.getRole().name());
            Map<Object,Object> response = new HashMap<>();
            response.put("login",customUser.getLogin());
            response.put("token",token);
            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/api/v1/auth/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response, null);
    }
}
