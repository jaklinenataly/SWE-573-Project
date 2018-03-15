package com.vakses.controller;

import com.vakses.model.dto.DonationDto;
import com.vakses.model.resource.DonationResource;
import com.vakses.service.DonationStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by veraxmedax on 26/02/2018.
 */
@RestController
@RequestMapping("/api/donations")
public class DonationController {
    private DonationStoreService donationStoreService;

    @Inject
    public DonationController(DonationStoreService donationStoreService) {
        this.donationStoreService = donationStoreService;
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<DonationResource> getAllDonations() {
        return donationStoreService.getAllDonationRequests();
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public DonationResource getDonationRequestById(@PathVariable final long id) {
        return donationStoreService.getAllDonationRequestsById(id);
    }

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public DonationResource createDonationRequest(@RequestBody final DonationDto donationDto) {
        return donationStoreService.createDonationRequest(donationDto);
    }
}
