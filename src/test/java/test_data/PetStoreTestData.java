package test_data;

import java.util.HashMap;
import java.util.Map;

public class PetStoreTestData {
    public PetStoreTestData() {
    }

    public PetStoreTestData(int i, String username, String firstname, String lastname, String email, String password, String phone, int userStatus) {
    }

    public Map<String, Object> expectedDataUserSetUp(Integer id, String username, String firstname, String lastname,
                                                     String email, String password, String phone, Integer userStatus){

        Map<String, Object> expectedDataUser = new HashMap<>();

        expectedDataUser.put("id", id);
        expectedDataUser.put("username", username);
        expectedDataUser.put("firstname", firstname);
        expectedDataUser.put("lastname", lastname);
        expectedDataUser.put("email", email);
        expectedDataUser.put("password", password);
        expectedDataUser.put("phone", phone);
        expectedDataUser.put("userStatus", userStatus);

        return expectedDataUser;

    }
}