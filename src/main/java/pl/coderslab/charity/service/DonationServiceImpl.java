package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;

@AllArgsConstructor
@Service
public class DonationServiceImpl implements DonationService {

    private static final String RETURN_DONATION_DETAILS = "user/userDonationDetails";
    private static final String RETURN_ADMIN_DONATION_DETAILS = "admin/adminDonationDetails";
    private static final String RETURN_INDEX_PAGE = "index";

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Override
    public void checkDonationAsPickedUp(Integer id) {
        donationRepository.checkDonationAsPickedUp(id);
    }

    @Override
    public String returnFormProperForRole() {
        if(userRepository.pickRoleIdbyUserId(userService.getUserFromContext().getId()) == 1){
            return RETURN_DONATION_DETAILS;
        } else if (userRepository.pickRoleIdbyUserId(userService.getUserFromContext().getId()) == 2){
            return RETURN_ADMIN_DONATION_DETAILS;
        } else {
            return RETURN_INDEX_PAGE;
        }
    }
}
