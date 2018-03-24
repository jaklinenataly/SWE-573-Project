package com.vakses.service;

import com.vakses.exception.InvalidBloodGroupTypeException;
import com.vakses.exception.UserNotFoundException;
import com.vakses.model.data.BloodGroup;
import com.vakses.model.entity.Subscription;
import com.vakses.model.entity.User;
import com.vakses.repository.SubscriptionRepository;
import com.vakses.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by veraxmedax on 23/03/2018.
 */
@Slf4j
@Service
public class SubscriptionService {

    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
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
        userRepository.save(user);
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
        if (!BloodGroup.getTypes().contains(bloodGroupType.toUpperCase())) {
            final String errorMessage = "Blood group type" + bloodGroupType + " is not valid!";
            log.error(errorMessage);
            throw new InvalidBloodGroupTypeException(errorMessage);
        }
    }

    private User assertUser(String userId) {
        final User user = userRepository.findById(userId);

        if (user == null) {
            final String errorMessage = "Unable to find user with id: " + userId;
            log.error(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }

        return user;
    }
}
