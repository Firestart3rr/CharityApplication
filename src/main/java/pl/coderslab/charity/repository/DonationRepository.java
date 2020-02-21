package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;

import javax.transaction.Transactional;

@Transactional
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query(value = "SELECT SUM(quantity) FROM donations", nativeQuery = true)
    int sumOfAllDonatedBags();
}
