package com.example.petclinic.controller;

import com.example.petclinic.model.Visit;
import com.example.petclinic.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("visit")
public class VisitController implements BasicController<Visit> {

    private VisitService visitService;

    public VisitController(VisitService visitService) {

        this.visitService = visitService;
    }

    @Override
    @PostMapping(value = "addVisit", produces = "application/json")
    public Visit add(@RequestBody Visit visit) {

        return this.visitService.add(visit);
    }

    @Override
    @GetMapping(value = "getVisitById/{id}", produces = "application/json")
    public Visit get(@PathVariable("id") Long id) {

        return this.visitService.get(id);
    }

    @Override
    @PutMapping(value = "modifyVisit", produces = "application/json")
    public Visit modify(@RequestBody Visit visit) {

        return this.visitService.modify(visit);
    }

    @Override
    @RequestMapping(value = "deleteVisit/{id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        this.visitService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "getAllVisits", produces = "application/json")
    public List<Visit> getAll() {

        return this.visitService.getAll();

    }

    @GetMapping("getVisitsByPetName/{name}")
    public List<Visit> getVisitsByPetName(@PathVariable String name) {
        return this.visitService.getVisitsByPetName(name);
    }
}
