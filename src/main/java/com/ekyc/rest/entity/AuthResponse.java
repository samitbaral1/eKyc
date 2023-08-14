package com.ekyc.rest.entity;

import com.ekyc.model.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class AuthResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int responseId;
    public String id;
    public String datetime;

}
