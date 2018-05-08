package com.vakses.service;

import com.vakses.exception.InvalidBloodGroupTypeException;
import com.vakses.model.data.BloodGroup;
import com.vakses.model.entity.Subscription;
import com.vakses.model.entity.User;
import com.vakses.model.resource.SubscriptionResource;
import com.vakses.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by veraxmedax on 23/03/2018.
 */
@Slf4j
@Service
public class SubscriptionService {

    private UserService userService;
    private SubscriptionRepository subscriptionRepository;
    private ConfigurableConversionService conversionService;

    @Autowired
    public SubscriptionService(UserService userService, SubscriptionRepository subscriptionRepository, ConfigurableConversionService conversionService) {
        this.userService = userService;
        this.subscriptionRepository = subscriptionRepository;
        this.conversionService = conversionService;
    }

    public boolean addSubscription(String userId, String bloodGroupType, String location) {
        validateType(bloodGroupType);

        final User user = assertUser(userId);

        for (Subscription subscription : user.getSubscriptions()) {
            if (subscription.getBloodGroupType().equals(bloodGroupType.toUpperCase())
                    && subscription.getLocation().equals(location.toUpperCase())) {
                log.info("User has already subscribed to blood group type: {} and location: {}", bloodGroupType, location);
                return false;
            }
        }

        final Subscription subscription = new Subscription(bloodGroupType.toUpperCase(), location.toUpperCase(), user);
        user.getSubscriptions().add(subscription);
        userService.storeUser(user);
        log.info("User is successfully subscribed to blood group type: {} and location: {}", bloodGroupType, location);

        return true;
    }

    public boolean removeSubscription(final long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId);
        if (subscription != null) {
            subscription.setUser(null);
            subscriptionRepository.save(subscription);
        }
        subscriptionRepository.delete(subscriptionId);
        return true;
    }

    private void validateType(String bloodGroupType) throws InvalidBloodGroupTypeException {
        if (!BloodGroup.getTypes().keySet().contains(bloodGroupType.toUpperCase())) {
            final String errorMessage = "Blood group type" + bloodGroupType + " is not valid!";
            log.error(errorMessage);
            throw new InvalidBloodGroupTypeException(errorMessage);
        }
    }

    private User assertUser(String userId) {
        return userService.findUserById(userId);
    }


    public Set<SubscriptionResource> getSubscriptionsByUser(String userId) {
        User user = assertUser(userId);
        return conversionService.convert(user.getSubscriptions(), Set.class);
    }
}
