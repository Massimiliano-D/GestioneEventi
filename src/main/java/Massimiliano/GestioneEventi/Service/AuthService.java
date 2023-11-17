package Massimiliano.GestioneEventi.Service;
import Massimiliano.GestioneEventi.Entities.Role;
import Massimiliano.GestioneEventi.Entities.Utente;
import Massimiliano.GestioneEventi.Exeptions.BadRequestException;
import Massimiliano.GestioneEventi.Exeptions.UnauthorizedException;
import Massimiliano.GestioneEventi.Payloads.AdminDTO;
import Massimiliano.GestioneEventi.Payloads.UtenteDTO;
import Massimiliano.GestioneEventi.Payloads.UtenteLoginDTO;
import Massimiliano.GestioneEventi.Repository.UtenteRepository;
import Massimiliano.GestioneEventi.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UtenteRepository utenteRepository;


    public String authenticateUser(UtenteLoginDTO body) {
        Utente utente = utenteService.findByEmail(body.email());
        if (body.password().equals(utente.getPassword())) {
           return jwtTools.createToken(utente);
        } else {
           throw new UnauthorizedException("Credenziali non valide!");
        }


    }

    public Utente registerUtente(UtenteDTO body) throws IOException {

        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già utilizzata!");
        });

        Utente newUtente = new Utente();
        newUtente.setAvatar("http://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        newUtente.setName(body.name());
        newUtente.setSurname(body.surname());
        newUtente.setPassword(bcrypt.encode(body.password()));
        newUtente.setEmail(body.email());
        newUtente.setRole(Role.UTENTE);
        newUtente.setUsername(body.username());
        Utente savedUtente = utenteRepository.save(newUtente);
        return newUtente;
    }
    public Utente registerAdmin(AdminDTO body) throws IOException {

        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già utilizzata!");
        });

        Utente newUtente = new Utente();
        newUtente.setAvatar("http://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        newUtente.setName(body.name());
        newUtente.setSurname(body.surname());
        newUtente.setPassword(bcrypt.encode(body.password()));
        newUtente.setEmail(body.email());
        newUtente.setRole(Role.ADMIN);
        newUtente.setUsername(body.username());
        Utente savedUtente = utenteRepository.save(newUtente);
        return newUtente;
    }
}
