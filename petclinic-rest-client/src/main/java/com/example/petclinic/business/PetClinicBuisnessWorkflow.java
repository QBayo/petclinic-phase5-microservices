package com.example.petclinic.business;

import com.example.petclinic.model.*;
import com.example.petclinic.service.OwnerService;
import com.example.petclinic.service.PetService;
import com.example.petclinic.service.VetService;
import com.example.petclinic.service.VisitService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PetClinicBuisnessWorkflow {

    private static final Logger log = LoggerFactory.getLogger(PetClinicBuisnessWorkflow.class.getName());

    private OwnerService ownerService;
    private PetService petService;
    private VetService vetService;
    private VisitService visitService;

    public PetClinicBuisnessWorkflow(OwnerService ownerService, PetService petService,VetService vetService,VisitService visitService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.visitService = visitService;
    }
   /* public PetClinicBuisnessWorkflow(PetService petService) {
        this.petService = petService;
    }
    public PetClinicBuisnessWorkflow(VetService vetService) {
        this.vetService = vetService;
    }
    public PetClinicBuisnessWorkflow(VisitService visitService) {
        this.visitService = visitService;
    }*/


    public void runBusiness() {

        // Create Owners
        Owner owner1 = Owner.builder().withName("Homer Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner2 = Owner.builder().withName("Marge Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner3 = Owner.builder().withName("Bart Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner4 = Owner.builder().withName("Lisa Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();

        // saveOwner
        ownerService.saveOwner(owner1);
        ownerService.saveOwner(owner2);
        ownerService.saveOwner(owner3);
        ownerService.saveOwner(owner4);

        // getAllOwners
        List<Owner> owners = ownerService.getAllOwners();
        owners.forEach(owner -> log.info(owner.toString()));

        // getOwnerByName
        String searchFor = "Homer Simpson";
        List<Owner> homers = ownerService.getOwnerByName(searchFor);

        AtomicInteger counter = new AtomicInteger(1);
        homers.forEach(homer -> {

            StringBuilder sb = new StringBuilder();
            sb.append(searchFor);
            sb.append(" ");
            sb.append(counter.getAndIncrement());
            sb.append(": ");
            sb.append(homer);

            log.info(sb.toString());
        });

        // modify owner
        Owner ownerModification = homers.get(0);
        ownerModification.setName("Homerus");

        ownerService.modifyOwner(ownerModification);

        log.info(ownerService.getOwnerByName("Homerus").toString());

        // delete owner
        ownerService.deleteOwner(ownerModification);


        // TODO add more business related logic here

        // Create Pets
        Pet pet1 = Pet.builder().withName("Strangles").withBirthDate(new Date()).withPetType(PetType.SNAKE).build();
        Pet pet2 = Pet.builder().withName("Mojo").withBirthDate(new Date()).withPetType(PetType.MONKEY).build();
        Pet pet3 = Pet.builder().withName("Pinchy").withBirthDate(new Date()).withPetType(PetType.LOBSTER).build();
        Pet pet4 = Pet.builder().withName("Plopper").withBirthDate(new Date()).withPetType(PetType.PIG).build();

        // savePet
        petService.savePet(pet1);
        petService.savePet(pet2);
        petService.savePet(pet3);
        petService.savePet(pet4);

        // getAllPets
        List<Pet> pets = petService.getAllPets();
        pets.forEach(pet -> log.info(pet.getName()));

        // getPetByName
        List<Pet> strangles = petService.getPetByName("Strangles");

        strangles.forEach(l-> log.info(l.toString()));

        // ***** Vet *****
        Vet vet1 = Vet.builder().withName("SuperVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).build();
        Vet vet2 = Vet.builder().withName("SuperDuperVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).withSpeciality(Speciality.RADIOLOGY).build();
        Vet vet3 = Vet.builder().withName("OutstandingVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).build();

        // saveVet
        vetService.saveVet(vet1);
        vetService.saveVet(vet2);
        vetService.saveVet(vet3);

        // getAllVets
        List<Vet> vets = vetService.getAllVets();
        vets.forEach(vet -> log.info(vet.toString()));

        // Create Visit
        Visit visit1 = Visit.builder().withDateOfVisit(new Date()).withDescription("Nice Visit!").build();
        Visit visit2 = Visit.builder().withDateOfVisit(new Date()).withDescription("Bad Visit!").build();
        Visit visit3 = Visit.builder().withDateOfVisit(new Date()).withDescription("No Visit!").build();
        Visit visit4 = Visit.builder().withDateOfVisit(new Date()).withDescription("Late Visit!").build();

        // saveVisit
        visitService.saveVisit(visit1);
        visitService.saveVisit(visit2);
        visitService.saveVisit(visit3);
        visitService.saveVisit(visit4);

        // getAllVisits
        List<Visit> visits = visitService.getAllVisits();

        // Change visit description
        Visit visit = visits.get(0);

        visit.setDescription("Hello Vet, Strangles is here for is first visit in 10 years");

        visitService.modifyVisit(visit);

        log.info(visitService.getAllVisits().toString());

        // deleteVisit
        visitService.deleteVisit(visit);

    }
}
