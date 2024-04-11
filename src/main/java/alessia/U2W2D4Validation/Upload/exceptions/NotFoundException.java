package alessia.U2W2D4Validation.Upload.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(int id){
        super("L'elemento con id " + id + " non è stato trovato");
    }
}
