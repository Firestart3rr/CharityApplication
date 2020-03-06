package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

@AllArgsConstructor
@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public void deleteInstitution(Institution institution, Donation donation) {
        if (donation.getInstitution() != institution) {
            donationRepository.detachDonationWithInstitutionFromInstitutions(institution.getId());
            institutionRepository.delete(institution);
        } else {
            institutionRepository.delete(institution);
        }
    }
}
