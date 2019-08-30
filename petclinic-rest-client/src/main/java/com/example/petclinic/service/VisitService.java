package com.example.petclinic.service;

import com.example.petclinic.model.Visit;
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
public class VisitService {

    private static final Logger log = LoggerFactory.getLogger(VisitService.class);

    private RestTemplate restTemplate;

    public VisitService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Visit saveVisit(Visit visit) {

        URI uri = URI.create("http://localhost:8084/visitapi/visit/addVisit");

        Visit response = restTemplate.postForObject(uri, visit, Visit.class);

        log.info(new StringBuilder().append("Saved Visit: ").append(response.toString()).toString());
        return response;

    }

    public List<Visit> getAllVisits() {

        URI uri = URI.create("http://localhost:8084/visitapi/visit/getAllVisits");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Visit> visits = mapper.convertValue(response, new TypeReference<List<Visit>>() {
        });

        log.info(new StringBuilder().append("Returning all visits.").toString());
        return visits;

    }

    public List<Visit> getVisitByName(String name) {

        // string replacement needed to create proper URL
        String modifiedName = name.replaceAll(" ", "%20");
        URI uri = URI.create("http://localhost:8084/visitapi/visit/getVisitByName/" + modifiedName);

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Visit> visits = mapper.convertValue(response, new TypeReference<List<Visit>>() {
        });

        log.info(new StringBuilder().append("Returning visit [").append(name).append("].").toString());

        return visits;

    }

    public void modifyVisit(Visit visit) {

        URI uri = URI.create("http://localhost:8084/visitapi/visit/modifyVisit");

        restTemplate.put(uri, visit);

        log.info(new StringBuilder().append("Modified Visit: ").append(visit.toString()).toString());

    }

    public boolean deleteVisit(Visit visit) {

        String uri = "http://localhost:8084/visitapi/visit/deleteVisit/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", visit.getId().toString());

        restTemplate.delete(uri, params);

        return true;
    }
}
