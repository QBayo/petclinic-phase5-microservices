package com.example.petclinic.repository;

import com.example.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VetRepository extends CrudRepository<Vet, Long> {

    List<Vet> findVetByName(String name);

}
