package com.gestiongarage.tp_gestion_garage.GestionUtilisateur.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.dto.LoginRequest;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.JwtResponse;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.RegisterRequest;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.SERVICE.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}