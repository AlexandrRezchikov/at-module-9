package org.example.data;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    public static final Map<String, Object> EXPECTED_USER_DATA = new HashMap<>();

    static {
        EXPECTED_USER_DATA.put("id", 2);
        EXPECTED_USER_DATA.put("email", "janet.weaver@reqres.in");
        EXPECTED_USER_DATA.put("first_name", "Janet");
        EXPECTED_USER_DATA.put("last_name", "Weaver");
        EXPECTED_USER_DATA.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
    }
}
