package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.repository.UserRepository;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    @Override
    public void changeStatus(AppUser appUser, Integer id) {
        if(appUser.isEnabled()){
            userRepository.blockUser(id);
        } else if (!appUser.isEnabled()){
            userRepository.unblockUser(id);
        }
    }

}
