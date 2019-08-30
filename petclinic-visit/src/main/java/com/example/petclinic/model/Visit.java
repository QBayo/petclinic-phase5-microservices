package com.example.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "Visit")
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateOfVisit;
    private String description;

    // Lazy fetch is better for performance than eager
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    @JsonIgnoreProperties({"visits"})
    private Pet pet;

    // Using the Visit as the owner of the relationship
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "visit_vet",
            joinColumns = @JoinColumn(name = "visit_id"),
            inverseJoinColumns = @JoinColumn(name = "vet_id")
    )
    @JsonIgnoreProperties({"visits"})
    private List<Vet> vets = new ArrayList<>();

    protected Visit() {

    }

    public Visit(Date dateOfVisit, String description) {
        this.dateOfVisit = dateOfVisit;
        this.description = description;
    }

    public static VisitBuilder builder() {
        return new VisitBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addVet(Vet vet) {
        this.vets.add(vet);
        vet.getVisits().add(this);
    }

    public void removeVet(Vet vet) {
        this.vets.remove(vet);
        vet.getVisits().remove(this);
    }

    public List<Vet> getVets() {
        return this.vets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visit{");
        sb.append("id=").append(id);
        sb.append(", dateOfVisit=").append(dateOfVisit);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static final class VisitBuilder {

        private Visit visit;

        private VisitBuilder() {
            visit = new Visit();
        }

        public VisitBuilder withDateOfVisit(Date dateOfVisit) {
            visit.setDateOfVisit(dateOfVisit);
            return this;
        }

        public VisitBuilder withDescription(String description) {
            visit.setDescription(description);
            return this;
        }

        public VisitBuilder withPet(Pet pet) {
            visit.setPet(pet);
            return this;
        }

        public VisitBuilder withVet(Vet vet) {
            visit.addVet(vet);
            vet.getVisits().add(visit);
            return this;
        }

        public Visit build() {
            return visit;
        }
    }
}
