package com.vakses.controller;

import com.vakses.model.dto.DonationDto;
import com.vakses.model.resource.DonationResource;
import com.vakses.service.DonationStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by veraxmedax on 26/02/2018.
 */
@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private DonationStoreService donationStoreService;

    @Autowired
    public DonationController(DonationStoreService donationStoreService) {
        this.donationStoreService = donationStoreService;
    }

    @GetMapping(value = "/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<DonationResource> getAllDonations() {
        return donationStoreService.getAllDonationRequests();
    }

    @GetMapping(value = "/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DonationResource getDonationRequestById(@PathVariable final long id) {
        return donationStoreService.getDonationRequestById(id);
    }

    @PostMapping(value = "/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public DonationResource createDonationRequest(@RequestBody final DonationDto donationDto) {
        return donationStoreService.createDonationRequest(donationDto);
    }
}
