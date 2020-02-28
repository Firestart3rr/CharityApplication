package pl.coderslab.charity.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.*;
import pl.coderslab.charity.service.UserServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;


    @Override
    public void run(String... args) throws Exception{
        int count = categoryRepository.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData(){

        Category category = new Category();
        category.setName("ubrania, które nadają się do ponownego użycia");
        categoryRepository.save(category);

        Category category2 = new Category();
        category2.setName("ubrania, do wyrzucenia");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setName("zabawki");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category4.setName("książki");
        categoryRepository.save(category4);

        Category category5 = new Category();
        category5.setName("inne");
        categoryRepository.save(category5);

        Institution institution = new Institution();
        institution.setName("Fundacja \"Dbam o Zdrowie\"");
        institution.setDescription("Cel i misja: Pomoc dzieciom z ubogich rodzin.");
        institutionRepository.save(institution);

        Institution institution2 = new Institution();
        institution2.setName("Fundacja “Dla dzieci\"");
        institution2.setDescription("Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.");
        institutionRepository.save(institution2);

        Institution institution3 = new Institution();
        institution3.setName("Fundacja \"A kogo\"");
        institution3.setDescription("Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki.");
        institutionRepository.save(institution3);

        Institution institution4 = new Institution();
        institution4.setName("Fundacja \"Bez domu\"");
        institution4.setDescription("Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania");
        institutionRepository.save(institution4);

        Donation donation = new Donation();
        donation.setCity("Warszawa");
        donation.setPickUpComment("ubrania dla potrzebujących");
        donation.setPickUpDate(LocalDate.of(2020, 2, 20));
        donation.setPickUpTime(LocalTime.of(16, 50, 20));
        donation.setQuantity(3);
        donation.setStreet("Prosta 51");
        donation.setZipCode("02-222");
        donation.setInstitution(institutionRepository.findById(3).get());
        donationRepository.save(donation);

        Donation donation2 = new Donation();
        donation2.setCity("Warszawa");
        donation2.setPickUpComment("zabawki dla dzieci");
        donation2.setPickUpDate(LocalDate.of(2020, 1, 4));
        donation2.setPickUpTime(LocalTime.of(10, 1, 59));
        donation2.setQuantity(5);
        donation2.setStreet("Złota 44");
        donation2.setZipCode("02-233");
        donation2.setInstitution(institutionRepository.findById(2).get());
        donationRepository.save(donation2);

        Donation donation3 = new Donation();
        donation3.setCity("Warszawa");
        donation3.setPickUpComment("schronisko dla bezdomnych");
        donation3.setPickUpDate(LocalDate.of(2019, 11, 22));
        donation3.setPickUpTime(LocalTime.of(11, 22, 33));
        donation3.setQuantity(1);
        donation3.setStreet("Wiejska 13");
        donation3.setZipCode("01-234");
        donation3.setInstitution(institutionRepository.findById(1).get());
        donationRepository.save(donation3);

        Role role1 = new Role();
        role1.setName("ROLE_USER");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        roleRepository.save(role2);

        AppUser appUser = new AppUser();
        appUser.setEmail("kuba.wziatka@gmail.com");
        appUser.setPassword("asdf");
        appUser.setRepassword("asdf");
        appUser.setFirstname("Jan");
        appUser.setLastname("Kowalski");
        userRepository.save(appUser);
        userService.saveUser(appUser);


        AppUser appUser2 = new AppUser();
        appUser2.setEmail("qwerty@asd.com");
        appUser2.setPassword("zxc");
        appUser2.setRepassword("zxc");
        appUser2.setFirstname("John");
        appUser2.setLastname("Smith");
        userRepository.save(appUser2);
        userService.saveAdmin(appUser2);

    }
}
