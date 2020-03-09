package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Transactional
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query(value = "SELECT SUM(quantity) FROM donations", nativeQuery = true)
    int sumOfAllDonatedBags();

    @Modifying
    @Query(value = "INSERT INTO donations(city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, app_user_id)" +
            "VALUES (:city, :pick_up_comment, :pick_up_date, :pick_up_time, :quantity, :street, :zip_code, :institution_id, :app_user_id)", nativeQuery = true)
    void saveDonation(@Param("city") String city, @Param("pick_up_comment") String pickUpCommnent, @Param("pick_up_date") LocalDate pickUpDate, @Param("pick_up_time") LocalTime pickUpTime,
                      @Param("quantity") Integer quantity, @Param("street") String street, @Param("zip_code") String zipCode, @Param("institution_id") Institution institution, @Param("app_user_id") AppUser appUser);

    @Modifying
    @Query(value = "DELETE FROM donations WHERE institution_id = :institutionId", nativeQuery = true)
    void detachDonationWithInstitutionFromInstitutions(@Param("institutionId") Integer id);

    List<Donation> findDonationsByAppUserId(Integer id);
}
