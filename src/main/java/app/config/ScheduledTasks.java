package app.config;

import app.entities.FitnessProgram;
import app.entities.User;
import app.service.EmailService;
import app.service.FitnessProgramService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ScheduledTasks {

    private final EmailService emailService;
    private final FitnessProgramService fitnessProgramService;

    public ScheduledTasks(EmailService emailService, FitnessProgramService fitnessProgramService) {
        this.emailService = emailService;
        this.fitnessProgramService = fitnessProgramService;
    }

//    @Scheduled(cron = "*/10 * * * * *") //every 10 seconds
    @Scheduled(cron = "0 0 12 * * ?")  //every day at noon
    public void myTask() {
        Map<User, List<FitnessProgram>> subscriptions = fitnessProgramService.getSubscriptions();

        subscriptions.forEach((key, value) -> emailService.sendNewTrainings(key.getEmail(), "New added trainings: " + value.stream().map(
                FitnessProgram::getName
        ).collect(Collectors.joining(", "))));
    }
}
