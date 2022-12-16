
package am.itspace.hotel.exception;

public class RoomAlreadyBookedExeption extends RuntimeException{

    public RoomAlreadyBookedExeption() {
    }

    public RoomAlreadyBookedExeption(String message) {
        super(message);
    }

    public RoomAlreadyBookedExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomAlreadyBookedExeption(Throwable cause) {
        super(cause);
    }

    public RoomAlreadyBookedExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}