package com.prograd.EmployeeManagement.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "assets")
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String assetsName;

    @NotEmpty
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    @Column(nullable = false)
    private String assetsType;


    @Column(nullable = false)
    private String assetsValue;

    @Column
    @Min(value = 1,message = "Entre organizationId")
    private int organizationId;

}
