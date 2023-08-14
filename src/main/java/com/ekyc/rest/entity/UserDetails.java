package com.ekyc.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_input_data")
public class UserDetails implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String lastname;
    @Column(length = 100, nullable = false)
    private String firstname;
    @Column(length = 100)
    private String middlename;
    @Column(length = 100, nullable = false)
    private String firstkana;
    @Column(length = 100, nullable = false)
    private String lastkana;
    @Column(length = 1, nullable = false)
    private int gender;             // 0 others, 1 male, 2 female
    @Column(length = 8, nullable = false)
    private String birthday;
    @Column(length = 8, nullable = false)
    private String zipcode;
    @Column(length = 100, nullable = false)
    private String prefectures;
    @Column(length = 100, nullable = false)
    private String city;
    @Column(length = 100, nullable = false)
    private String street;
    @Column(length = 100, nullable = false)
    private String building;
    @Column(length = 100, nullable = false)
    private String prefkana;
    @Column(length = 100, nullable = false)
    private String citykana;
    @Column(length = 100, nullable = false)
    private String streetkana;
    @Column(length = 100, nullable = false)
    private String buildingkana;
    @JsonIgnore
    @OneToOne(mappedBy = "inputData", cascade = CascadeType.ALL)
    private UserInformationWithIdentification userInformation;

}
