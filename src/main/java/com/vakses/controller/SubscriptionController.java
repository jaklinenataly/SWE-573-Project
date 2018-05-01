package com.vakses.controller;

import com.vakses.model.resource.SubscriptionResource;
import com.vakses.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

/**
 * Created by veraxmedax on 23/03/2018.
 */
@RestController
@RequestMapping("/api/notification/subscriptions")
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PutMapping("/add")
    //@PreAuthorize("hasAuthority('user.p')")
    public ResponseEntity<Boolean> addSubscription(@RequestParam(value = "userId") final String userId,
                                                   @RequestParam(value = "type") final String bloodGroupType,
                                                   @RequestParam(value = "location") final String location) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.addSubscription(userId, bloodGroupType, location));
    }

    @PutMapping("/remove/{subscriptionId}")
    public ResponseEntity<Boolean> removeSubscription(@PathVariable final long subscriptionId) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.removeSubscription(subscriptionId));
    }

    @GetMapping()
    //@PreAuthorize("hasRole('user.r')")
    public ResponseEntity<Set<SubscriptionResource>> getUserSubscriptions(@RequestParam(value = "userId") final String userId, Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getSubscriptionsByUser(userId));
    }

}
