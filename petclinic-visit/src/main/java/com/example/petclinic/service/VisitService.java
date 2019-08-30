package com.example.petclinic.service;

import com.example.petclinic.model.Visit;
import com.example.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService implements BasicService<Visit> {

    private VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {

        this.visitRepository = visitRepository;
    }

    @Override
    public Visit add(Visit visit) {

        return this.visitRepository.save(visit);
    }

    @Override
    public Visit get(Long id) {

        Optional optional = this.visitRepository.findById(id);

        Visit result = null;
        if (optional.isPresent()) {
            result = (Visit) optional.get();
        }
        return result;
    }

    @Override
    public Visit modify(Visit visit) {

        return this.visitRepository.save(visit);
    }

    @Override
    public void delete(Long id) {

        Visit visitToDelete = get(id);
        this.visitRepository.delete(visitToDelete);

    }

    @Override
    public List<Visit> getAll() {

        return (List<Visit>) this.visitRepository.findAll();
    }

    public List<Visit> getVisitsByPetName(String name) {

        return this.visitRepository.findVisitByPetName(name);
    }
}
