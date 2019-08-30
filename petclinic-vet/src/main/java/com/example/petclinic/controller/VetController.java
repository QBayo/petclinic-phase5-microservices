package com.example.petclinic.controller;


import com.example.petclinic.model.Vet;
import com.example.petclinic.service.VetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vet")
public class VetController implements BasicController<Vet> {

    private VetService vetService;

    public VetController(VetService vetService) {

        this.vetService = vetService;
    }

    @Override
    @PostMapping(value = "addVet", produces = "application/json")
    public Vet add(@RequestBody Vet vet) {

        return this.vetService.add(vet);
    }

    @Override
    @GetMapping(value = "getVetById/{id}", produces = "application/json")
    public Vet get(@PathVariable("id") Long id) {

        return this.vetService.get(id);
    }

    @Override
    @PutMapping(value = "modifyVet", produces = "application/json")
    public Vet modify(@RequestBody Vet vet) {

        return this.vetService.modify(vet);
    }

    @Override
    @RequestMapping(value = "deleteVet/{id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        this.vetService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "getAllVets", produces = "application/json")
    public List<Vet> getAll() {

        return this.vetService.getAll();
    }

    @GetMapping(value = "getVetByName/{name}", produces = "application/json")
    public List<Vet> getVetByName(@PathVariable String name) {

        return this.vetService.getVetByName(name);
    }
}
