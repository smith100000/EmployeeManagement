package com.prograd.EmployeeManagement.controllers;


import com.prograd.EmployeeManagement.models.Assets;
import com.prograd.EmployeeManagement.models.Organisation;
import com.prograd.EmployeeManagement.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {
    @Autowired
    private OrganisationService organisationService;
    public OrganisationController(OrganisationService organisationService){
        this.organisationService=organisationService;
    }
    @PostMapping
    public ResponseEntity<String> saveOrganisation(@RequestBody @Valid Organisation organisation){
        organisationService.saveOrganisation(organisation);
        return new ResponseEntity<>("Created successfully",HttpStatus.CREATED);
    }
    @GetMapping
    public List<Organisation> getAllOrganisation(){
        return organisationService.getAllOrganisation();
    }
    @GetMapping("{id}")
    public ResponseEntity<Organisation> getById(@PathVariable("id")int organisationId)
    {
        try {
            Organisation og = organisationService.getById(organisationId);
            return new ResponseEntity<>(og, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable("id")int organisationId,@RequestBody Organisation organisation)
    {
        return new ResponseEntity<Organisation>(organisationService.updateOrganisation(organisation,organisationId), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganisation(@PathVariable("id")int organisationId)
    {
        try{
            organisationService.deleteOrganisation(organisationId);
            return new ResponseEntity<String>(" data deleted successfully",HttpStatus.OK);

        }
        catch (EmptyResultDataAccessException e){
            return new ResponseEntity<String>("organisation data Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
