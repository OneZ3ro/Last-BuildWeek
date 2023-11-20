package group4.LastBuildWeek.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("Elemento con id : " + id + " non trovato.");
    }

    public NotFoundException( String string){
            super("Record con id : " + string + " non trovato.");
        }
    }

