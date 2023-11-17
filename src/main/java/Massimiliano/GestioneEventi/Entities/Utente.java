package Massimiliano.GestioneEventi.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String avatar;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    }
