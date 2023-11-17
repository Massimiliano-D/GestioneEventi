package Massimiliano.GestioneEventi.Payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EventiDTO (@NotEmpty(message = "Campo del titolo Obbligatorio!") String titolo,
                         @NotEmpty(message = "Campo della descrizione Obbligatorio!") String  descrizione,
                         @NotEmpty(message = "Campo della data Obbligatorio!")LocalDate data,
                         @NotEmpty(message = "Campo del luogo Obbligatorio!") String luogo,
                         @NotEmpty(message = "Campo del numero posti Obbligatorio!") int numero_posti

){

        }


