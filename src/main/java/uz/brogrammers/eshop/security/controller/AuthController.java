package uz.brogrammers.eshop.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brogrammers.eshop.security.JwtProvider;
import uz.brogrammers.eshop.user.entity.RoleName;
import uz.brogrammers.eshop.user.entity.User;
import uz.brogrammers.eshop.user.service.RoleService;
import uz.brogrammers.eshop.user.service.UserService;

import java.time.ZonedDateTime;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new LoginResponse(jwt));

    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest request) {

        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        var role = roleService.findByName(RoleName.ROLE_USER);
        String firstName = StringUtils.capitalize(request.getName().trim().toLowerCase());
        String lastName = StringUtils.capitalize(request.getSurname().trim().toLowerCase());

        User user = User.builder()
                .created(ZonedDateTime.now())
                .name(firstName)
                .surname(lastName)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        var savedUser = userService.save(user);

        return ResponseEntity.ok().body(savedUser);
    }

}
