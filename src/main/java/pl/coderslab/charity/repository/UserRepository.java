package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserByEmail(String email);
}
