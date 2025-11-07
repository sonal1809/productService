package com.example.productservice.commons;


import com.example.productservice.dtos.common.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthCommon {

    private static RestTemplate restTemplate;

    public AuthCommon(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static boolean validateToken(String tokenValue){
//        String url = "http://localhost:8080/users/validate/" + tokenValue;
        String url = "http://UserService/users/validate/" + tokenValue;
        UserDTO userDTO =  restTemplate.getForObject(
                url,
                UserDTO.class
        );
        if(userDTO == null) {
            return false;
        }
        return true;
    }
}
