package com.example.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "Pet")
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date birthDate;
    private PetType petType;

    // Lazy fetch is better for performance than eager
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties("pets")
    private Owner owner;

    @OneToMany(
            mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"pet"})
    private List<Visit> visits = new ArrayList<>();

    protected Pet() {

    }

    public Pet(String name, Date birthDate, PetType petType) {

        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
    }

    public static PetBuilder builder() {
        return new PetBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // This is needed to update the relationship between Visit and Pet when adding a Visit
    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPet(this);
    }

    // This is needed to update the relationship between Visit and Pet when removing a Visit
    public void removeVisit(Visit visit) {
        visits.remove(visit);
        visit.setPet(null);
    }

    // only include id field when generating equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pet {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", petType=").append(petType);
        sb.append('}');
        return sb.toString();
    }

    public static final class PetBuilder {
        private Pet pet;

        private PetBuilder() {
            pet = new Pet();
        }

        public PetBuilder withId(Long id) {
            pet.setId(id);
            return this;
        }

        public PetBuilder withName(String name) {
            pet.setName(name);
            return this;
        }

        public PetBuilder withBirthDate(Date birthDate) {
            pet.setBirthDate(birthDate);
            return this;
        }

        public PetBuilder withPetType(PetType petType) {
            pet.setPetType(petType);
            return this;
        }

        public PetBuilder withOwner(Owner owner) {
            pet.setOwner(owner);
            owner.getPets().add(pet);
            return this;
        }

        public PetBuilder withVisit(Visit visit) {
            pet.addVisit(visit);
            visit.setPet(pet);
            return this;
        }

        public Pet build() {
            return pet;
        }
    }
}
