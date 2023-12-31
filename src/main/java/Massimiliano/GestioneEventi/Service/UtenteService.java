package Massimiliano.GestioneEventi.Service;


import Massimiliano.GestioneEventi.Entities.Utente;
import Massimiliano.GestioneEventi.Exeptions.NotFoundExceptionUtente;
import Massimiliano.GestioneEventi.Repository.UtenteRepository;
import com.cloudinary.Cloudinary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UtenteRepository utenteRepository;


    public Page<Utente> getUtente(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return utenteRepository.findAll(pageable);
    }

    public Utente findById(int id) throws NotFoundExceptionUtente {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundExceptionUtente(id));
    }

    public void findByIdAndDelete(int id) throws NotFoundExceptionUtente {
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }

    public Utente findByIdAndUpdate(int id, Utente body) throws NotFoundExceptionUtente {
        Utente found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        return utenteRepository.save(found);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundExceptionUtente("Utente con email " + email + " non trovato!"));
    }
}
