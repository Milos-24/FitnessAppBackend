package app.controller;

import app.dtos.MembershipDTO;
import app.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
@CrossOrigin(origins = "http://localhost:4200")
public class MembershipController {

    private final MembershipService membershipService;
    @PostMapping
    public ResponseEntity<?> createMembership(
            @RequestParam int paymentTypeId,
            @RequestParam String card,
            @RequestParam String username,
            @RequestParam int fitnessProgramId
            ) {
    return membershipService.createMembership(paymentTypeId, card, username, fitnessProgramId);
    }

    @GetMapping("/{user_id}")
    public List<MembershipDTO> getMembershipHistory(@PathVariable("user_id") int user_id) {
        return membershipService.getMembershipHistory(user_id);
    }



}
