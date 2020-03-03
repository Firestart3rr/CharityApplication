package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;

public interface UserService {
    AppUser findUserByEmail(String email);

    void saveUser(AppUser appUser);

    void saveAdmin(AppUser appUser);

    boolean checkIfValidOldPassword(AppUser appUser);
}
