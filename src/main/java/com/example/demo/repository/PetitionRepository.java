package com.example.demo.repository;
import com.example.demo.model.Petition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Repository
public class PetitionRepository {
    private final List<Petition> petitions = new ArrayList<>();
    private Long nextId = 1L;

    public PetitionRepository() {
        // Initial data
        petitions.add(new Petition(nextId++, "Save the Forests", "A petition to protect forests."));
        petitions.add(new Petition(nextId++, "Support Renewable Energy", "A petition to promote renewable energy."));
    }

    public List<Petition> findAll() {
        return petitions;
    }

    public Optional<Petition> findById(Long id) {
        return petitions.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Petition save(Petition petition) {
        petition.setId(nextId++);
        petitions.add(petition);
        return petition;
    }
}
