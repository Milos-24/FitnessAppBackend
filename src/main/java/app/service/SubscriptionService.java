package app.service;

import app.dtos.CategoryDTO;
import app.entities.Category;
import app.entities.Subscription;
import app.entities.User;
import app.repositories.CategoryRepository;
import app.repositories.SubscriptionRepository;
import app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public ResponseEntity<?> subscribe(CategoryDTO categoryDTO, String username)
    {
        User user = userRepository.findByUsername(username);

        categoryDTO.getCategories().forEach(
                s -> {
                    Category category = categoryRepository.findByName(s);
                    Subscription subscription = Subscription.builder()
                            .user(user)
                            .category(category)
                            .build();

                    subscriptionRepository.save(subscription);
                }
        );

        return ResponseEntity.ok().build();

    }

}
