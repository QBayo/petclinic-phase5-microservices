package com.example.petclinic.service;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService implements BasicService<Vet> {

    private VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {

        this.vetRepository = vetRepository;
    }

    @Override
    public Vet add(Vet vet) {

        return this.vetRepository.save(vet);
    }

    @Override
    public Vet get(Long id) {

        Optional optional = this.vetRepository.findById(id);

        Vet result = null;
        if (optional.isPresent()) {
            result = (Vet) optional.get();
        }

        return result;
    }

    @Override
    public Vet modify(Vet vet) {

        return this.vetRepository.save(vet);
    }

    @Override
    public void delete(Long id) {

        Vet vetToDelete = get(id);
        this.vetRepository.delete(vetToDelete);

    }

    @Override
    public List<Vet> getAll() {

        return (List<Vet>) this.vetRepository.findAll();
    }

    public List<Vet> getVetByName(String name) {

        return this.vetRepository.findVetByName(name);
    }
}
