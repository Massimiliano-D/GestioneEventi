package Massimiliano.GestioneEventi.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AdminDTO(@NotEmpty(message = "Campo del nome Obbligatorio!") String name,
                        @NotEmpty(message = "Campo del cognome Obbligatorio!") String surname,
                        @NotEmpty(message = "La password è un campo obbligatorio!")
                        String password,
                        @NotEmpty(message = "Campo della email Obbligatorio!")
                        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida") String email,
                        @NotNull(message = "Campo della user name Obbligatorio!") String username,
                       @NotEmpty(message = "il codice è obbligatorio")
                       @Pattern(regexp = "adminevent", message = "il codice non è valido") String admincode,
                        String avatar) {

}
