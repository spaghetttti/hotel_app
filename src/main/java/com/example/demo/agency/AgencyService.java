package com.example.demo.agency;

import com.example.demo.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> addNewAgency(Agency newAgency) {
        // Hash the password before storing
        hashPassword(newAgency);

        agencyRepository.save(newAgency);
        return new ResponseEntity<>(
                "New agency has been created",
                HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAgencyById(Long id) {
        if (!agencyRepository.existsById(id)) {
            return new ResponseEntity<>(
                    "Agency with id " + id + " doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }
        agencyRepository.deleteById(id);
        return new ResponseEntity<>(
                "Agency deleted successfully",
                HttpStatus.OK);
    }

    public ResponseEntity<String> updateAgency(Long id, Agency agencyInfo) {
        Optional<Agency> agencyToUpdateOptional = agencyRepository.findById(id);
        if (agencyToUpdateOptional.isEmpty()) {
            return new ResponseEntity<>(
                    "Agency with id " + id + " doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }

        Agency existingAgency = agencyToUpdateOptional.get();

        // Update agency information based on agencyInfo
        Optional.ofNullable(agencyInfo.getName()).ifPresent(existingAgency::setName);
        Optional.ofNullable(agencyInfo.getDiscount()).filter(price -> price > 0 && price <= 100).ifPresent(existingAgency::setDiscount);
        Optional.ofNullable(agencyInfo.getLoginUsername()).ifPresent(existingAgency::setLoginUsername);
        Optional.ofNullable(agencyInfo.getPassword()).ifPresent(existingAgency::setPassword);

        agencyRepository.save(existingAgency); // Save the updated agency
        return new ResponseEntity<>(
                "Agency updated successfully",
                HttpStatus.OK);
    }

    private void hashPassword(Agency agency) {
        String hashedPassword = PasswordHasher.hashPassword(agency.getPassword());
        agency.setPassword(hashedPassword);
    }
}
