package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuAppTestData {

    //map for inner json of expected data
    public static Map<String, String> bookingDatesMapper(String checkin, String checkout){

        Map<String,String> map = new HashMap<>();
       // map.put("checkin","2018-01-01"); //HARD CODING
        map.put("checkin",checkin);
        map.put("checkout",checkout);
        return map;
    }

    //map for outer json of expected data
    public static Map<String, Object> herokuAppMapper(String firstname, String lastname,Integer totalprice, Boolean depositpaid, Map<String,String> bookingdates, String additionalneeds){
    //Use Integer as a wrapper class to deal with the "null" values.

        Map<String, Object> map = new HashMap<>();
        map.put("firstname",firstname);
        map.put( "lastname", lastname);
        map.put("totalprice", totalprice);
        map.put( "depositpaid", depositpaid);
        map.put( "bookingdates", bookingdates);
        map.put( "additionalneeds", additionalneeds);
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