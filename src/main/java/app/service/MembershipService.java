package app.service;

import app.dtos.MembershipDTO;
import app.entities.FitnessProgram;
import app.entities.Membership;
import app.entities.Payment;
import app.entities.User;
import app.repositories.FitnessProgramRepository;
import app.repositories.MembershipRepository;
import app.repositories.PaymentRepository;
import app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    public ResponseEntity<?> createMembership(int paymentTypeId, String card, String username, int fitnessProgramId) {

        Payment payment = paymentRepository.findById(paymentTypeId).orElse(null);
        User user = userRepository.findByUsername(username);
        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(fitnessProgramId).orElse(null);

        Membership membership = new Membership(user,fitnessProgram,card,payment, new Date());


        membershipRepository.save(membership);

        return ResponseEntity.ok().build();
    }

    public List<MembershipDTO> getMembershipHistory(int user_id) {
        List<Membership> memberships = membershipRepository.findByUserId(user_id);
        return memberships.stream().map(membership -> new MembershipDTO(membership.getCard(), membership.getFitnessProgram().getCreator().getUsername(), membership.getDate(), membership.getPaymentType().getName(), membership.getFitnessProgram().getName())).collect(Collectors.toList());
    }
}
