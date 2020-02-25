package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.AppUser;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserByEmail(String email);

    @Modifying
    @Query(value = "INSERT INTO user_role(app_user_id, role_id) VALUES(:app_user_id, 1)", nativeQuery = true)
    void appendRoleToUser(@Param("app_user_id") int id);
}
