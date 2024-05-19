package app.service;

import app.dtos.FitnessProgramDTO;
import app.entities.*;
import app.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FitnessProgramService {

    private final FitnessProgramRepository fitnessProgramRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final CommentRepository commentRepository;
    private final MembershipRepository membershipRepository;
    private final ProgramExerciseRepository programExerciseRepository;
    public List<FitnessProgram> getAllFitnessPrograms() {
        return fitnessProgramRepository.findAll();
    }

    public Optional<FitnessProgram> getFitnessProgramById(int id) {
        return fitnessProgramRepository.findById(id);
    }

    public List<FitnessProgram> getAllFitnessProgramsByCategory(String category) {

        return fitnessProgramRepository.findByCategory(categoryRepository.findByName(category));
    }


    public ResponseEntity<?> addFitnessProgram(FitnessProgramDTO fitnessProgramDTO, String username)
    {
        ArrayList<Membership> memberships = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        ArrayList<ProgramExercise> programExercises = new ArrayList<>();
        Category category = categoryRepository.findByName(fitnessProgramDTO.getSelectedCategory());
        FitnessProgram fitnessProgram = FitnessProgram.builder()
                .date(new Date())
                .level(fitnessProgramDTO.getLevel())
                .creator(userRepository.findByUsername(username))
                .active(false)
                .price(Double.parseDouble(fitnessProgramDTO.getPrice()))
                .name(fitnessProgramDTO.getName())
                .duration(fitnessProgramDTO.getDuration())
                .description(fitnessProgramDTO.getDescription())
                .category(category)
                .location(fitnessProgramDTO.getLocation())
                .comments(comments)
                .memberships(memberships)
                .programExercises(programExercises)
                .build();

        fitnessProgramRepository.save(fitnessProgram);

        return new ResponseEntity<>(fitnessProgram, HttpStatus.OK);
    }

    public List<FitnessProgram> getAllFitnessProgramsByLocation(String location) {
        return fitnessProgramRepository.findByLocation(location);
    }

    @Transactional
    public Map<User, List<FitnessProgram>> getSubscriptions()
    {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        return subscriptions.stream()
                .collect(Collectors.toMap(
                        Subscription::getUser,
                        subscription -> subscription.getCategory().getFitnessPrograms().stream()
                                .filter(fitnessProgram -> {
                                    LocalDateTime programDate = fitnessProgram.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                    return programDate.isAfter(LocalDateTime.now().minusHours(24));
                                }).collect(Collectors.toList())));
    }


    public Set<String> getAllFitnessProgramsLocations() {
        List<FitnessProgram> programs = fitnessProgramRepository.findAll();

        return programs.stream().map(FitnessProgram::getLocation).collect(Collectors.toSet());
    }


    public List<FitnessProgram> searchByProperty(String property, String value) {
        Specification<FitnessProgram> specification = (root, query, criteriaBuilder) -> switch (property.toLowerCase()) {
            case "name" -> criteriaBuilder.equal(root.get("name"), value);
            case "price" -> criteriaBuilder.equal(root.get("price"), Double.parseDouble(value));
            case "duration" -> criteriaBuilder.equal(root.get("duration"), value);
            case "description" -> criteriaBuilder.equal(root.get("description"), value);
            case "level" -> criteriaBuilder.equal(root.get("level"), Integer.parseInt(value));
            case "active" -> criteriaBuilder.equal(root.get("active"), Boolean.parseBoolean(value));
            case "creator" ->
                    criteriaBuilder.equal(root.get("creator"), userRepository.findByUsername(value));
            case "location" -> criteriaBuilder.equal(root.get("location"), value);
            case "category" ->
                    criteriaBuilder.equal(root.get("category"), categoryRepository.findByName(value));
            default -> throw new IllegalArgumentException("Invalid property: " + property);
        };
        return fitnessProgramRepository.findAll(specification);
    }

    public ResponseEntity<?> deleteFitnessProgram(int id) {

        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(id).orElse(null);
        if(fitnessProgram!=null)
        {
            commentRepository.deleteAll(fitnessProgram.getComments());
            membershipRepository.deleteAll(fitnessProgram.getMemberships());
            programExerciseRepository.deleteAll(fitnessProgram.getProgramExercises());
        }


    fitnessProgramRepository.deleteById(id);


        return ResponseEntity.ok().build();
    }
}