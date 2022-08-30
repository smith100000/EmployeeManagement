package com.prograd.EmployeeManagement.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "organisationId")
@Table(name = "organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer organisationId;

    @Column(nullable = false)
    @NotEmpty
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String organisationName;

    @Column(nullable = false)
    @NotEmpty
    private String organisationAddress;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "OrganizationId",referencedColumnName = "organisationId")
    private Set<Employee> Employee=new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "organizationId",referencedColumnName = "organisationId")
    private Set<Assets> Assets =new HashSet<>();
}
