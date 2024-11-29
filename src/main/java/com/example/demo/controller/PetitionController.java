package com.example.demo.controller;
import com.example.demo.model.Petition;
import com.example.demo.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class PetitionController {

    @Autowired
    private PetitionRepository petitionRepository;

    // View all petitions
    @GetMapping
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitionRepository.findAll());
        return "petitions";
    }

    // View a single petition
    @GetMapping("/{id}")
    public String viewPetition(@PathVariable Long id, Model model) {
        petitionRepository.findById(id).ifPresent(petition -> model.addAttribute("petition", petition));
        return "petition";
    }

    // Form to create a new petition
    @GetMapping("/new")
    public String createPetitionForm(Model model) {
        model.addAttribute("petition", new Petition(null, "", ""));
        return "createPetition";
    }

    // Handle submission of new petition
    @PostMapping("/new")
    public String createPetition(@ModelAttribute Petition petition) {
        petitionRepository.save(petition);
        return "redirect:/petitions";
    }

    // Search for petitions by title
    @GetMapping("/search")
    public String searchPetitions(@RequestParam String keyword, Model model) {
        List<Petition> results = petitionRepository.findAll().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        model.addAttribute("results", results);
        return "searchResults";
    }
}
