package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Donation;

import javax.transaction.Transactional;

@Transactional
public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
