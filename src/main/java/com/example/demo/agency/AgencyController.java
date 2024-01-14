package com.example.demo.agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/agencies")
public class AgencyController {

    private final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    public List<Agency> getAgencies() {
        return agencyService.getAgencies();
    }

    @GetMapping("/{id}")
    public Agency getAgencyById(@PathVariable Long id) {
        return agencyService.getAgencyById(id);
    }

    @PostMapping
    public ResponseEntity<String> addNewAgency(@RequestBody Agency agency) {
        return agencyService.addNewAgency(agency);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgency(@PathVariable Long id) {
        return agencyService.deleteAgencyById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAgency(@PathVariable Long id, @RequestBody Agency agencyInfo) {
        return agencyService.updateAgency(id, agencyInfo);
    }
}
