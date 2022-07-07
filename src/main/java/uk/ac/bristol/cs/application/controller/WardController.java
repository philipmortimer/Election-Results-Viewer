package uk.ac.bristol.cs.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import uk.ac.bristol.cs.application.model.Ward;
import uk.ac.bristol.cs.application.model.WardElectionResult;
import uk.ac.bristol.cs.application.model.WardListData;
import uk.ac.bristol.cs.application.repository.WardRepository;
import uk.ac.bristol.cs.application.NoSuchElementException;
import uk.ac.bristol.cs.application.model.ModelClass;

@RestController
public class WardController {

    private final WardRepository repository;
    
    WardController(WardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/wards")
    List<WardListData> getAllWards() {
        return repository.findAll().stream().map(x -> new WardListData(x)).toList();
    }

    @GetMapping(path="/api/ward/results/{id}", produces="application/json")
    String getWardResultsById(@PathVariable String id) {
        Optional<Ward> w = repository.findById(id);
        if(w.isEmpty()) throw new NoSuchElementException(Ward.class, id);
        WardElectionResult res = new WardElectionResult(w.get());
        return ModelClass.renderJSON(res, WardElectionResult.class, id);
    }
}