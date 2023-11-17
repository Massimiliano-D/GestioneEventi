package Massimiliano.GestioneEventi.Service;

import Massimiliano.GestioneEventi.Entities.Eventi;
import Massimiliano.GestioneEventi.Entities.Utente;
import Massimiliano.GestioneEventi.Exeptions.BadRequestException;
import Massimiliano.GestioneEventi.Exeptions.NotFoundExceptionEventi;
import Massimiliano.GestioneEventi.Payloads.EventiDTO;
import Massimiliano.GestioneEventi.Repository.EventiRepository;
import Massimiliano.GestioneEventi.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventiService {
    @Autowired
    private EventiRepository eventiRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public Eventi save(EventiDTO body) {
        Eventi newEventi = new Eventi();

        newEventi.setTitolo(body.titolo());
        newEventi.setData(body.data());
        newEventi.setLuogo(body.luogo());
        newEventi.setDescrizione(body.descrizione());
        newEventi.setNumero_posti(body.numero_posti());
        return eventiRepository.save(newEventi);
    }

    public Page<Eventi> getEventi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventiRepository.findAll(pageable);
    }

    public Eventi findById(int id) {

        return eventiRepository.findById(id).orElseThrow(() -> new NotFoundExceptionEventi(id));

    }

    public Eventi findAndUpdateById(int id, Eventi body) {
        Eventi found1 = this.findById(id);

        found1.setId(id);
        found1.setTitolo(body.getTitolo());
        found1.setData(body.getData());
        found1.setLuogo(body.getLuogo());
        found1.setDescrizione(body.getDescrizione());
        found1.setNumero_posti(body.getNumero_posti());


        return found1;

    }


    public void findAndDeleteById(int id) {
        Eventi found1 = this.findById(id);
        eventiRepository.delete(found1);
    }


}

