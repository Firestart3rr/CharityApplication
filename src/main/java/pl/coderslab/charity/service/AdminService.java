package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;

public interface AdminService {

    void chaneStatus(AppUser appUser, Integer id);

}
