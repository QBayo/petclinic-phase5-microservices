package com.example.petclinic;

import com.example.petclinic.controller.OwnerController;
import com.example.petclinic.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PetClinicOwner {

    private static final Logger log = LoggerFactory.getLogger(PetClinicOwner.class.getName());

    private static ConfigurableApplicationContext context;
    private static OwnerController ownerController;

    public static void main(String[] args) {

        context = SpringApplication.run(PetClinicOwner.class, args);
        generateData();
    }


    private static void generateData() {

        // Need a reference to the OwnerController to run our tests.
        // We use the context to retrieve managed beans by name.
        // The name of the bean is the type of bean (it's name) in camelcase, with the first letter lowercase (by default).
        ownerController = (OwnerController) context.getBean("ownerController");

        // Create Owners
        Owner owner1 = Owner.builder().withName("Homer Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner2 = Owner.builder().withName("Marge Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner3 = Owner.builder().withName("Bart Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();
        Owner owner4 = Owner.builder().withName("Lisa Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build();

        // Add Owners
        ownerController.add(owner1);
        ownerController.add(owner2);
        ownerController.add(owner3);
        ownerController.add(owner4);

        // getAllOwners
       // List<Owner> owners = ownerController.getAllOwners();
        //owners.forEach(owner -> log.info(owner.toString()));

    }
}
