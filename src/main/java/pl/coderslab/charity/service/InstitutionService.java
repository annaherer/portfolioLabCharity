package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepo;

    public List<Institution> findAll() {
        return institutionRepo.findAll();
    }

    public Institution findById(Integer id) {
        return institutionRepo.findById(id).orElseThrow();
    }

    public Institution save(Institution institution) {
        return institutionRepo.save(institution);
    }

    public void deleteById(Integer id) {
        institutionRepo.deleteById(id);
    }
}