package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "podaj email")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "podaj haslo")
    private String password;

    @Transient
    private String repassword;
    @Transient
    private String oldpassword;

    @NotBlank(message = "podaj imie")
    private String firstname;

    @NotBlank(message = "podaj nazwisko")
    private String lastname;

    private boolean enabled = true;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
