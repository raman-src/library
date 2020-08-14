package com.smu.rest.library.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/register/v1")
public class RegistrationController {

    @PostMapping
    public ResponseEntity<String> register(@RequestBody String email) {

        if (email.contains("@") && email.contains(".")) {
            return new ResponseEntity<>(md5Java(email), HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid email: " + email, HttpStatus.BAD_REQUEST);
    }

    private String md5Java(String email) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(email.getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            System.out.println(hash);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
