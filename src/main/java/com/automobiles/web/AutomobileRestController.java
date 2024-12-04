package com.automobiles.web;

import com.automobiles.domain.Automobile;
import com.automobiles.domain.AutomobileRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AutomobileRestController {

    private final AutomobileRepository repository;

    @PostMapping("/automobiles")
    @ResponseStatus(HttpStatus.CREATED)
    public Automobile saveAutomobile(@Valid @RequestBody Automobile automobile) {
        automobile.setId(UUID.randomUUID());
        return repository.save(automobile);
    }

    @GetMapping("/automobiles")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Automobile> getAllAutomobiles() {
        return repository.findAll();
    }

    @GetMapping("/automobiles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Automobile getAutomobileById(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid automobile id: " + id));
    }

    @GetMapping("/automobiles-names")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllAutomobilesByName() {
        List<Automobile> collection = repository.findAll();
        return collection.stream()
                .map(Automobile::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}
