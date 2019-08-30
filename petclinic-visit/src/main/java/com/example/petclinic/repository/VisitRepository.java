package com.example.petclinic.repository;

import com.example.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    List<Visit> findVisitByPetName(String name);

}
