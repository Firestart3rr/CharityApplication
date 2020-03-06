package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;

public interface AdminService {

    void changeStatus(AppUser appUser, Integer id);

}
