package com.prograd.EmployeeManagement.services;

import com.prograd.EmployeeManagement.models.Organisation;

import java.util.List;

public interface OrganisationService {
    Organisation saveOrganisation(Organisation organisation);
    List<Organisation> getAllOrganisation();
    Organisation getById(int organisationId);
    Organisation updateOrganisation(Organisation organisation,int organisationId);
    void deleteOrganisation(int organisationId);
}
