package paymentgateway.usermanager.service;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import paymentgateway.usermanager.appUser.AppUser;
import paymentgateway.usermanager.appUser.AppUserRole;
import paymentgateway.usermanager.exception.UserNotFoundException;
import paymentgateway.usermanager.repo.AppUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
    public AppUser enableAppUser(AppUser user) {
        Optional<AppUser> userExists;
        userExists = appUserRepository.findByEmail(user.getEmail());
        if (userExists.isPresent()) {
            user.setEnabled(true);
            user.setLocked(false);
        }
        return appUserRepository.save(user);
    }
    public List<AppUser> findAllMarchand(){
        return appUserRepository.findAll();
    }
    public AppUser updateMarchand(AppUser marchand){
        return appUserRepository.save(marchand);
    }

    public AppUser findMarchandById(Long id){
        return appUserRepository.findById(id) .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteMarchand(Long id){
        appUserRepository.deleteById(id);
    }
}