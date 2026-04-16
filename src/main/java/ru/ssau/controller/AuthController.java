package ru.ssau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ssau.dto.LoginRequest;
import ru.ssau.dto.JwtResponse;
import ru.ssau.entity.Teacher;
import ru.ssau.repository.TeacherRepository;
import ru.ssau.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final TeacherRepository teacherRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          TeacherRepository teacherRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getFullname(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateToken(userDetails);
        Teacher teacher = teacherRepository.findByFullName(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                teacher.getId(),
                teacher.getFullName(),
                teacher.getRole()
        ));
    }
}