package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;

public interface UserService {

    AppUser findUserByEmail(String email);

    void saveUser(AppUser appUser, String role);

    boolean checkIfValidOldPassword(AppUser appUser);
}
