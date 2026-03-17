package com.gestiongarage.tp_gestion_garage.GestionUtilisateur.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.Repository.UserRepository;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.dto.LoginRequest;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.JwtResponse;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.RegisterRequest;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.User;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.SERVICE.AuthService;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.SERVICE.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse register(RegisterRequest request) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà pris");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return mapToJwtResponse(user, jwtToken);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        var jwtToken = jwtService.generateToken(user);
        return mapToJwtResponse(user, jwtToken);
    }

    private JwtResponse mapToJwtResponse(User user, String token) {
        return JwtResponse.builder()
                .token(token)
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}