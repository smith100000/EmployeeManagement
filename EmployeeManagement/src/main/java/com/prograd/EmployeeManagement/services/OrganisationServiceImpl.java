package com.prograd.EmployeeManagement.services;
import com.prograd.EmployeeManagement.models.Organisation;
import com.prograd.EmployeeManagement.repository.OrganisationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService{
    @Autowired
    private OrganisationRepository organisationRepository;
    public  OrganisationServiceImpl(OrganisationRepository organisationRepository){
        this.organisationRepository=organisationRepository;
    }
    @Override
    public Organisation saveOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public List<Organisation> getAllOrganisation() {
        return (List<Organisation>) organisationRepository.findAll();
    }

    @Override
    public Organisation getById(int organisationId) {
        return organisationRepository.findById(organisationId).orElseThrow();
    }

    @Override
    public Organisation updateOrganisation(Organisation organisation, int organisationId) {
        Organisation existingOrganisation=organisationRepository.findById(organisationId).orElseThrow();
        existingOrganisation.setOrganisationName(organisation.getOrganisationName());
        existingOrganisation.setOrganisationAddress(organisation.getOrganisationAddress());
        organisationRepository.save(existingOrganisation);
        return existingOrganisation ;
    }

    @Override
    public void deleteOrganisation(int organisationId) {
        organisationRepository.findById(organisationId);
        organisationRepository.deleteById(organisationId);

    }
}
