package Massimiliano.GestioneEventi.Controller;

import Massimiliano.GestioneEventi.Entities.Eventi;
import Massimiliano.GestioneEventi.Payloads.EventiDTO;
import Massimiliano.GestioneEventi.Service.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventiController {
    @Autowired
    private EventiService eventiService;

    @GetMapping("")
    public Page<Eventi> getEventi(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String orderBy) {
        return eventiService.getDispositivo(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Eventi saveEventi(@RequestBody EventiDTO body) {
        return eventiService.save(body);
    }

    @GetMapping("/{id}")
    public Eventi findById(@PathVariable int id) {
        return eventiService.findById(id);
    }


    @PutMapping("/{id}")
    public Eventi findAndUpdateById(@PathVariable int id, @RequestBody Eventi body) {
        return eventiService.findAndUpdateById(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDeleteById(@PathVariable int id) {
        eventiService.findAndDeleteById(id);
    }


}