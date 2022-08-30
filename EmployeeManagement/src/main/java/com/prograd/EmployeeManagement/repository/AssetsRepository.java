package com.prograd.EmployeeManagement.repository;

import com.prograd.EmployeeManagement.models.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets,Integer> {

}
