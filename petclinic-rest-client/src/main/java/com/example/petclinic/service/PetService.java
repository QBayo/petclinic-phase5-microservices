package com.example.petclinic.service;

import com.example.petclinic.model.Pet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private RestTemplate restTemplate;

    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pet savePet(Pet pet) {

        URI uri = URI.create("http://localhost:8082/petapi/pet/addPet");

        Pet response = restTemplate.postForObject(uri, pet, Pet.class);

        log.info(new StringBuilder().append("Saved Pet: ").append(response.toString()).toString());
        return response;

    }

    public List<Pet> getAllPets() {

        URI uri = URI.create("http://localhost:8082/petapi/pet/getAllPets");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Pet> pets = mapper.convertValue(response, new TypeReference<List<Pet>>() {
        });

        log.info(new StringBuilder().append("Returning all pets.").toString());
        return pets;

    }

    public List<Pet> getPetByName(String name) {

        // string replacement needed to create proper URL
        String modifiedName = name.replaceAll(" ", "%20");
        URI uri = URI.create("http://localhost:8082/petapi/pet/getPetByName/" + modifiedName);

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Pet> pets = mapper.convertValue(response, new TypeReference<List<Pet>>() {
        });

        log.info(new StringBuilder().append("Returning pet [").append(name).append("].").toString());

        return pets;

    }

    public void modifyPet(Pet pet) {

        URI uri = URI.create("http://localhost:8082/petapi/pet/updatePet");

        restTemplate.put(uri, pet);

        log.info(new StringBuilder().append("Modified Pet: ").append(pet.toString()).toString());

    }

    public boolean deletePet(Pet pet) {

        String uri = "http://localhost:8082/petapi/pet/deletePet/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", pet.getId().toString());

        restTemplate.delete(uri, params);

        return true;
    }
}
