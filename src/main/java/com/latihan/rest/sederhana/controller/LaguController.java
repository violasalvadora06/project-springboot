package com.latihan.rest.sederhana.controller;

import com.latihan.rest.sederhana.exception.ResourceNotFoundException;
import com.latihan.rest.sederhana.model.Lagu;
import com.latihan.rest.sederhana.repository.LaguRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LaguController {

    @Autowired
    LaguRepository laguRepository;

    @GetMapping("/musik")
    public List<Lagu> getAllMusik() {
        return laguRepository.findAll();
    }

    @PostMapping("/musik")
    public Lagu createLagu(@Valid @RequestBody Lagu lagu) {
        return laguRepository.save(lagu);
    }

    // Get by id
    @GetMapping("/musik/{id}")
    public Lagu getLaguById(@PathVariable(value = "id") Long laguId) {
        return laguRepository.findById(laguId)
                .orElseThrow(() -> new ResourceNotFoundException("Lagu", "id", laguId));
    }

    // Update
    @PutMapping("/musik/{id}")
    public Lagu updateLagu(@PathVariable(value = "id") Long laguId,
                             @Valid @RequestBody Lagu laguDetails) {
        Lagu lagu = laguRepository.findById(laguId)
                .orElseThrow(() -> new ResourceNotFoundException("Lagu", "id", laguId));
        lagu.setTitle(laguDetails.getTitle());
        lagu.setPencipta(laguDetails.getPencipta());
        Lagu updatedLagu = laguRepository.save(lagu);
        return updatedLagu;
    }

    @DeleteMapping("/musik/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Lagu lagu = laguRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Lagu", "id", noteId));

        laguRepository.delete(lagu);

        return ResponseEntity.ok().build();
    }
}
