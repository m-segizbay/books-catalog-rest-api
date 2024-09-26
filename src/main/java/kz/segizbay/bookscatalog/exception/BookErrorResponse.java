package kz.segizbay.bookscatalog.exception;

import java.time.Instant;

public class BookErrorResponse {
    private String message;
    private long timestamp;

    public BookErrorResponse(String message) {
        this.message = message;
        this.timestamp = Instant.now().toEpochMilli();;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
