package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ContactForm {
    private String name;
    private String email;
    private String phone;
    private String country;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zipcode;
    private String message;
}
