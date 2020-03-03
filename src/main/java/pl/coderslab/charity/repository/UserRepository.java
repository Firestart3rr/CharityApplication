package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.charity.entity.AppUser;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserByEmail(String email);

    @Modifying
    @Query(value = "INSERT INTO user_role(app_user_id, role_id) VALUES(:app_user_id, 1)", nativeQuery = true)
    void appendRoleToUser(@Param("app_user_id") int id);


    @Query(value = "SELECT * FROM app_user JOIN user_role ON app_user.id = user_role.app_user_id WHERE role_id = 2;", nativeQuery = true)
    List<AppUser> selectAdmins();


    @Query(value = "SELECT * FROM app_user JOIN user_role ON app_user.id = user_role.app_user_id WHERE role_id = 1;", nativeQuery = true)
    List<AppUser> selectUsers();

    @Modifying
    @Query(value = "UPDATE app_user SET enabled = false WHERE id = :id", nativeQuery = true)
    void blockUser(@Param("id") int id);

    @Modifying
    @Query(value = "UPDATE app_user SET enabled = true WHERE id = :id", nativeQuery = true)
    void unblockUser(@Param("id") int id);

    @Query(value = "SELECT * FROM app_user WHERE id = :id", nativeQuery = true)
    AppUser getPasswordByUserId(@Param("id") int id);
}
