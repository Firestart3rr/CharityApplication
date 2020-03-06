package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;

public interface InstitutionService {

    void deleteInstitution(Institution institution, Donation donation);

}
