package Massimiliano.GestioneEventi.Controller;

import Massimiliano.GestioneEventi.Entities.Utente;
import Massimiliano.GestioneEventi.Exeptions.BadRequestException;
import Massimiliano.GestioneEventi.Payloads.AdminDTO;
import Massimiliano.GestioneEventi.Payloads.UtenteDTO;
import Massimiliano.GestioneEventi.Payloads.UtenteLoginDTO;
import Massimiliano.GestioneEventi.Payloads.UtenteLoginSuccDTO;
import Massimiliano.GestioneEventi.Service.AuthService;
import Massimiliano.GestioneEventi.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginSuccDTO login(@RequestBody UtenteLoginDTO body) {

        return new UtenteLoginSuccDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Utente saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUtente(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Utente saveAdmin(@RequestBody @Validated AdminDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerAdmin(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
