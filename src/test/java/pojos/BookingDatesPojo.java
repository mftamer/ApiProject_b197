package pojos;

public class BookingDatesPojo {


//            1) Create all variables as private (Encapsulation)
        private String checkin;
        private String checkout;

//            2) Create constructors with and without parameters

    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

//            3) Create getter ad setter for all variables

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


//            4) Create toString() method to print everything on the console

    @Override
    public String toString() {
        return "BookingDatePojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }


//      "bookingdates": {
//        "checkin": "2018-01-01",
//                "checkout": "2019-01-01"
//    }
}
