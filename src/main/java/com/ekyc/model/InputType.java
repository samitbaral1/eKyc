package com.ekyc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.transform.sax.SAXResult;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputType {
    private String image;
    private String token;
}
