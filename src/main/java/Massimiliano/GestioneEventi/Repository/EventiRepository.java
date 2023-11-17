package Massimiliano.GestioneEventi.Repository;

import Massimiliano.GestioneEventi.Entities.Eventi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventiRepository extends JpaRepository<Eventi, Integer> {

}