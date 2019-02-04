package pl.edu.pjwstk.photoapp.domain.reservations;

import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationDateAndTime {

    @NotBlank
    private String date;
    @NotBlank
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDateTime getLocalDateTime() {
        String datetime = date + "T" + time + ":00";
        return LocalDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME);
    }
}
