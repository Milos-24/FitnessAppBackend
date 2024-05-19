package app.controller;

import app.dtos.CategoryDTO;
import app.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subscribe")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    @PostMapping("/{username}")
    public ResponseEntity<?> subscribeToCategories(@RequestBody CategoryDTO categoryDTO,
                                                   @PathVariable("username") String username) {
        subscriptionService.subscribe(categoryDTO, username);
        return ResponseEntity.ok().build();
    }
}
