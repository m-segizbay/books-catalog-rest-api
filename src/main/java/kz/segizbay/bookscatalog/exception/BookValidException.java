package kz.segizbay.bookscatalog.exception;

public class BookValidException extends RuntimeException{
    public BookValidException(String msg){
        super(msg);
    }
}
