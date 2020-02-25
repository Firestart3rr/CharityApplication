package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findAppUserByEmail(email);
    }


    @Override
    public void saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role userRole= roleRepository.findByName("ROLE_USER");
        appUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(appUser);
        userRepository.appendRoleToUser(appUser.getId());
    }
}
