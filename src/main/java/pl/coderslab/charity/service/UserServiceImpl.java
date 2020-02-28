package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findAppUserByEmail(email);
    }


    @Override
    public void saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        //TODO hashowanie powtorzonego hasla, wydzielic pojedyncza metode
        Role userRole = roleRepository.findByName("ROLE_USER");
        appUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(appUser);
    }

    @Override
    public void saveAdmin(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        appUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(appUser);
    }

    public AppUser getUserFromContext() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getAppUser();
    }
}
