package com.vakses.service;

import com.vakses.exception.InvalidEmailException;
import com.vakses.exception.InvalidEntityException;
import com.vakses.model.entity.DonationEntity;
import com.vakses.model.entity.Subscription;
import com.vakses.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by veraxmedax on 23/03/2018.
 */
@Slf4j
@Service
public class NotificationService {
    private JavaMailSender emailSender;
    private SubscriptionRepository subscriptionRepository;
    private Environment environment;

    @Autowired
    public NotificationService(JavaMailSender emailSender, SubscriptionRepository subscriptionRepository, Environment environment) {
        this.emailSender = emailSender;
        this.subscriptionRepository = subscriptionRepository;
        this.environment = environment;
    }

    public void sendEmail(String email, DonationEntity donationEntity) {

        assertEmailProperties(email, donationEntity);

        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(environment.getProperty("username"));
        mail.setTo(email);
        mail.setSubject("New Donation Request Notification from BlooderHood");
        mail.setText(getText(donationEntity));
        emailSender.send(mail);
    }

    public void notifySubscribers(DonationEntity donationEntity) {
        final List<Subscription> subscriptions = subscriptionRepository.
                findByBloodGroupTypeAndLocation(donationEntity.getBloodGroup(), donationEntity.getCity());

        subscriptions.stream().forEach(subscription -> {
            log.info("Email will be sent to: {} for blood group type: {} and location: {}",
                    subscription.getUser().getEmail(), donationEntity.getBloodGroup(), donationEntity.getCity());
            sendEmail(subscription.getUser().getEmail(), donationEntity);
        });
    }

    private void assertEmailProperties(String email, DonationEntity donationEntity) {
        if (StringUtils.isEmpty(email)) {
            final String errorMessage = "Email must not be null!";
            log.info(errorMessage);
            throw new InvalidEmailException(errorMessage);
        }

        if (Objects.isNull(donationEntity)) {
            final String errorMessage = "Donation must not be null!";
            log.info(errorMessage);
            throw new InvalidEntityException(errorMessage);
        }
    }

    private static String getText(DonationEntity donationEntity) {
        return String.format("Blood Group: %s, city: %s, hospital: %s, contact: %s",
                donationEntity.getBloodGroup(), donationEntity.getCity(), donationEntity.getHospital(), donationEntity.getContact());
    }

}
