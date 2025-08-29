package com.webproject.web.controller;

import com.webproject.web.dto.DtoUser;
import com.webproject.web.jwt.AuthRequest;
import com.webproject.web.jwt.AuthResponse;
import com.webproject.web.jwt.JwtService;
import com.webproject.web.repository.IUserRepository;
import com.webproject.web.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<DtoUser> register(@RequestBody AuthRequest request){
        DtoUser newUser = authService.register(request);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

        }catch (Exception e){
            System.out.println("jdfnjksdfkls");
        }


        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
















}
