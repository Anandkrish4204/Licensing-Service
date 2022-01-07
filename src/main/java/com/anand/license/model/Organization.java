package com.anand.license.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Organization {
    private String id;
    private String name;
    private String contactName;
    private String contactNumber;
    private String contactEmail;
}
