package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerokuResponsePojo {

    private Integer bookingId;
    private HerokuAppPojo booking;

    public HerokuResponsePojo() {
    }

    public HerokuResponsePojo(Integer bookingId, HerokuAppPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public HerokuAppPojo getBooking() {
        return booking;
    }

    public void setBooking(HerokuAppPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}