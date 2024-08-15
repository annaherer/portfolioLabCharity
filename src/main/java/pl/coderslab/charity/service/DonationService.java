package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
@AllArgsConstructor
public class DonationService {
    private final DonationRepository donationRepo;

    public Integer countAllBags() {
        return donationRepo.countAllBags();
    }

    public Integer countAllDonations() {
        return donationRepo.countAllDonations();
    }

    public Donation save(Donation donation) {
        return donationRepo.save(donation);
    }
}