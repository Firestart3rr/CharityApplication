package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;

@AllArgsConstructor
@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    @Override
    public void checkDonationAsPickedUp(Integer id) {
        donationRepository.checkDonationAsPickedUp(id);
    }
}
