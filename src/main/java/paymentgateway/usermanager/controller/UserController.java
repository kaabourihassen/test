package paymentgateway.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import paymentgateway.usermanager.appUser.AppUser;
import paymentgateway.usermanager.repo.AppUserRepository;
import paymentgateway.usermanager.service.AppUserService;
import paymentgateway.usermanager.registration.RegistrationRequest;
import paymentgateway.usermanager.security.JwtResponse;
import paymentgateway.usermanager.security.jwt.AuthTokenFilter;
import paymentgateway.usermanager.security.jwt.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
public class UserController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    AuthTokenFilter authTokenFilter;



    @PostMapping("/auth/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody RegistrationRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser userDetails = (AppUser) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
