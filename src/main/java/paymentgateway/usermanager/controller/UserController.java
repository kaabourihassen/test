package paymentgateway.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import paymentgateway.usermanager.appUser.AppUser;
import paymentgateway.usermanager.registration.RegistrationService;
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
    private RegistrationService registrationService;
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

    @GetMapping("marchand/all")
    public ResponseEntity<List<AppUser>> getAllMarchand(){
        List<AppUser> marchands=appUserService.findAllMarchand();
        return new ResponseEntity<>(marchands, HttpStatus.OK);
    }

    @GetMapping("marchand/find/{id}")
    public ResponseEntity<AppUser> getMarchandById(@PathVariable("id")Long id){
        AppUser marchand=appUserService.findMarchandById(id);
        return new ResponseEntity<>(marchand, HttpStatus.OK);
    }

    @PostMapping("marchand/add")
    public ResponseEntity<AppUser> addMarchand(@RequestBody RegistrationRequest marchand) {
        AppUser newMarchand = registrationService.addMarchand(marchand);
        return new ResponseEntity<>(newMarchand, HttpStatus.CREATED);
    }

    @PutMapping("marchand/update")
    public ResponseEntity<AppUser> updateEmployee(@RequestBody AppUser marchand) {
        AppUser updateMarchand = appUserService.updateMarchand(marchand);
        return new ResponseEntity<>(updateMarchand, HttpStatus.OK);
    }

    @DeleteMapping("marchand/delete/{id}")
    public ResponseEntity<?> deleteMarchand(@PathVariable("id") Long id) {
        appUserService.deleteMarchand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
