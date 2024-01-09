package com.example.demo.agency;

import com.example.demo.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyService {
    private final AgencyRepository agencyRepository;

    @Autowired
    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    public Agency getAgencyById(Long id) {
        return agencyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agency not found"));
    }

    public String addNewAgency(Agency newAgency) {
        // Hash the password before storing
        hashPassword(newAgency);

        agencyRepository.save(newAgency);
        return "New agency has been created";
    }

    public String deleteAgencyById(Long id) {
        if (!agencyRepository.existsById(id)) {
            throw new IllegalArgumentException("Agency with id " + id + " doesn't exist");
        }
        agencyRepository.deleteById(id);
        return "Agency deleted successfully";
    }

    public String updateAgency(Long id, Agency agencyInfo) {
        Optional<Agency> agencyToUpdateOptional = agencyRepository.findById(id);
        if (agencyToUpdateOptional.isEmpty()) {
            throw new IllegalArgumentException("Agency with id " + id + " doesn't exist");
        }

        Agency existingAgency = agencyToUpdateOptional.get();

        // Update agency information based on agencyInfo
        // Example: Optional.ofNullable(agencyInfo.getName()).ifPresent(existingAgency::setName);
        // Add similar lines for other agency properties

        agencyRepository.save(existingAgency); // Save the updated agency

        return "Agency updated successfully";
    }

    private void hashPassword(Agency agency) {
        String hashedPassword = PasswordHasher.hashPassword(agency.getPassword());
        agency.setPassword(hashedPassword);
    }
}
