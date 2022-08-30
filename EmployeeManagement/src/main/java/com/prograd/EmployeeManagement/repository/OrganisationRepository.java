package com.prograd.EmployeeManagement.repository;

import com.prograd.EmployeeManagement.models.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
}
