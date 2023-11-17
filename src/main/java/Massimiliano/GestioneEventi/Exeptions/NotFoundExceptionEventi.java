package Massimiliano.GestioneEventi.Exeptions;

public class NotFoundExceptionEventi extends RuntimeException {
    public NotFoundExceptionEventi(int id) {
        super("Evento:" + " " + id + " " + "NON trovato!!");
    }
}
