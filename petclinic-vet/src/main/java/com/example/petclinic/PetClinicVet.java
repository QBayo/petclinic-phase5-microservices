package com.example.petclinic;

import com.example.petclinic.controller.VetController;
import com.example.petclinic.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class PetClinicVet {

    private static ConfigurableApplicationContext context;
    private static VetController vetController;

    public static void main(String[] args) {

        context = SpringApplication.run(PetClinicVet.class, args);
        generateData();
    }



    private static void generateData() {

        // Need a reference to the OwnerController to run our tests.
        // We use the context to retrieve managed beans by name.
        // The name of the bean is the type of bean (it's name) in camelcase, with the first letter lowercase (by default).
        vetController = (VetController) context.getBean("vetController");

        // ***** Vet *****
        Vet vet1 = Vet.builder().withName("SuperVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).build();
        Vet vet2 = Vet.builder().withName("SuperDuperVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).withSpeciality(Speciality.RADIOLOGY).build();
        Vet vet3 = Vet.builder().withName("OutstandingVet").withSpeciality(Speciality.DENTISTRY).withSpeciality(Speciality.SURGERY).build();

        vetController.add(vet1);
        vetController.add(vet2);
        vetController.add(vet3);


    }
}
