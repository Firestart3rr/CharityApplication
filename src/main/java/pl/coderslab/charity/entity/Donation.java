package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "liczba musi byc wieksza od zera")
    private Integer quantity;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    private Institution institution;

    @ManyToOne
    private AppUser appUser;

    @NotBlank(message = "podaj ulice")
    private String street;
    @NotBlank(message = "podaj miasto")
    private String city;
    @NotBlank(message = "podaj kod pocztowy")
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "niewłaściwy format")
    private String zipCode;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

    private boolean isPickedUp;
    private LocalDateTime createDate;
}
