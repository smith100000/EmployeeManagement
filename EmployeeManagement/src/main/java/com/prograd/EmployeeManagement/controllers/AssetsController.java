package com.prograd.EmployeeManagement.controllers;

import com.prograd.EmployeeManagement.models.Assets;
import com.prograd.EmployeeManagement.services.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/assets")
public class AssetsController {
    @Autowired
    private AssetsService assetsService;
    public AssetsController(AssetsService assetsService){
        this.assetsService=assetsService;
    }
    @PostMapping
    public ResponseEntity<String> saveAssets(@RequestBody @Valid Assets assets){
        assetsService.saveAssets(assets);
        return new ResponseEntity<>("Created successfully",HttpStatus.CREATED);
    }
    @GetMapping
    public List<Assets> getAllAssets(){
        return assetsService.getAllAssets();
    }
    @GetMapping("{id}")
    public ResponseEntity<Assets> getAssetsById(@PathVariable("id")int id)
    {
        try {
            Assets am = assetsService.getAssetsById(id);
            return new ResponseEntity<>(am, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateAssets(@PathVariable("id")int id,@RequestBody Assets assets)
    {
        try {
            assetsService.updateAssets(assets,id);
            return new ResponseEntity<>("update Successfully",HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAssets(@Valid @PathVariable("id") int id){
        assetsService.deleteAssets(id);
        return new ResponseEntity<String>("Assets data deleted successfully",HttpStatus.OK);
    }

    }




