package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuAppTestData {

    //map for inner json of expected data
    public static Map<String, String> bookingDatesMapper(String checkin, String checkout){

        Map<String,String> map = new HashMap<>();
       // map.put("checkin","2018-01-01"); //HARD CODING
        if (checkin !=null) {
            map.put("checkin", checkin);
        }
        if (checkout !=null) {
            map.put("checkout", checkout);
        }
        return map;
    }

    //map for outer json of expected data
    public static Map<String, Object> herokuAppMapper(String firstname, String lastname,Integer totalprice, Boolean depositpaid, Map<String,String> bookingdates, String additionalneeds){
    //Use Integer as a wrapper class to deal with the "null" values.

        Map<String, Object> map = new HashMap<>();
        if (firstname != null) {
            map.put("firstname", firstname);
        }
        if (lastname != null) {
            map.put("lastname", lastname);
        }
        if (totalprice != null) {
            map.put("totalprice", totalprice);
        }
        if (depositpaid != null) {
            map.put("depositpaid", depositpaid);
        }
        if (bookingdates != null) {
            map.put("bookingdates", bookingdates);
        }
        if (additionalneeds != null) {
            map.put("additionalneeds", additionalneeds);
        }
        return map;
    }



}


//These are here just to copy and paste and check if needed.
//                "firstname": "Jane",
//                        "lastname": "Doe",
//                        "totalprice": 111,
//                        "depositpaid": true,
//                        "bookingdates": {
//                        "checkin": "2018-01-01",
//                        "checkout": "2019-01-01"
//                        },
//                        "additionalneeds": "Extra pillows please"