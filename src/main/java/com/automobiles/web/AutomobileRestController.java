package com.automobiles.web;

import com.automobiles.domain.Automobile;
import com.automobiles.domain.AutomobileRepository;
import com.automobiles.exception.AutoWasDeletedException;
import com.automobiles.exception.ThereIsNoSuchAutoException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
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
        Automobile receivedAutomobile = repository.findById(id)
                .orElseThrow(ThereIsNoSuchAutoException::new);
        if (receivedAutomobile.getDeleted()) {
            throw new AutoWasDeletedException();
        }
        return receivedAutomobile;
    }

    @PutMapping("/automobiles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Automobile refreshAutomobile(@PathVariable UUID id, @RequestBody Automobile automobile) {
        return repository.findById(id)
                .map(entity -> {
                    entity.checkColor(automobile);
                    entity.setName(automobile.getName());
                    entity.setColor(automobile.getColor());
                    entity.setUpdateDate(automobile.getUpdateDate());
                    if (entity.getDeleted()) {
                        throw new AutoWasDeletedException();
                    }
                    return repository.save(entity);
                })
                .orElseThrow(ThereIsNoSuchAutoException::new);
    }

    @DeleteMapping("/automobiles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "automobile", key = "#id")
    public String removeAutomobileById(@PathVariable UUID id) {
        Automobile deletedAutomobile = repository.findById(id)
                .orElseThrow(ThereIsNoSuchAutoException::new);
        deletedAutomobile.setDeleted(Boolean.TRUE);
        repository.save(deletedAutomobile);
        return "Deleted";
    }

    @Hidden
    @DeleteMapping("/automobiles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllAutomobiles() {
        repository.deleteAll();
    }

    @GetMapping("/automobiles-names")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllAutomobilesByName() {
        List<Automobile> collection = repository.findAll();
        throw new ThereIsNoSuchAutoException();
//        return collection.stream()
//                .map(Automobile::getName)
//                .sorted()
//                .collect(Collectors.toList());
    }
}
