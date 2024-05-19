package app.controller;

import app.dtos.FitnessProgramDTO;
import app.entities.FitnessProgram;
import app.service.FitnessProgramService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fitnessprograms")
@CrossOrigin(origins = "http://localhost:4200")
public class FitnessProgramController {


    private final FitnessProgramService fitnessProgramService;

    @GetMapping("/all")
    public ResponseEntity<List<FitnessProgram>> getAllFitnessPrograms() {
        List<FitnessProgram> programs = fitnessProgramService.getAllFitnessPrograms();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> addFitnessProgram(@RequestBody FitnessProgramDTO fitnessProgramDTO, @PathVariable("username") String username)
    {
        return fitnessProgramService.addFitnessProgram(fitnessProgramDTO,username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFitnessProgram(@PathVariable("id") int id)
    {
        return fitnessProgramService.deleteFitnessProgram(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FitnessProgram> getFitnessProgramById(@PathVariable("id") int id) {
        Optional<FitnessProgram> program = fitnessProgramService.getFitnessProgramById(id);
        if (program.isPresent()) {
            return new ResponseEntity<>(program.orElse(null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<FitnessProgram>> getFitnessProgramsByCategory(@PathVariable("category") String category) {
        List<FitnessProgram> programs = fitnessProgramService.getAllFitnessProgramsByCategory(category);

        return new ResponseEntity<>(programs, HttpStatus.OK);
    }


    @GetMapping("/location/{location}")
    public ResponseEntity<List<FitnessProgram>> getFitnessProgramsByLocation(@PathVariable("location") String location) {
        List<FitnessProgram> programs = fitnessProgramService.getAllFitnessProgramsByLocation(location);
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }

    @Operation(summary = "Get all locations", description = "Returns a list of locations, this has to be " +
            "within FitnessProgram entity because location doesn't exist as a separate entity!")
    @GetMapping("/location/all")
    public ResponseEntity<Set<String>> getFitnessProgramsAllLocation() {
        Set<String> locations = fitnessProgramService.getAllFitnessProgramsLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }


    @GetMapping("/search")
    public List<FitnessProgram> searchFitnessPrograms(
            @RequestParam String property,
            @RequestParam String value) {
        return fitnessProgramService.searchByProperty(property, value);
    }
}
