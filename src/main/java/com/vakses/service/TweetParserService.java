package com.vakses.service;

import com.vakses.model.data.BloodGroup;
import com.vakses.model.data.Hospitals;
import com.vakses.model.entity.DonationEntity;
import com.vakses.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Service
public class TweetParserService {
    private BloodGroup bloodGroup;
    private Hospitals hospitals;
    private DonationRepository donationRepository;

    @Autowired
    public TweetParserService(BloodGroup bloodGroup, Hospitals hospitals, DonationRepository donationRepository) {
        this.bloodGroup = bloodGroup;
        this.hospitals = hospitals;
        this.donationRepository = donationRepository;
    }

    public void parse(Tweet tweet) {
        DonationEntity storedEntity = donationRepository.findByTweetId(tweet.getId());

        if (storedEntity != null) {
            return;
        }

        DonationEntity donationEntity = new DonationEntity();
        final String tweetText = tweet.getText().toUpperCase();

        // TODO : move into new methods
        bloodGroup.getTypes().forEach(type -> {
            if (tweetText.contains(type)) {
                donationEntity.setBloodGroup(type);
                return;
            }
        });

        if (donationEntity.getBloodGroup() != null) {
            hospitals.getNames().forEach((key, value) -> {
                if (tweetText.contains(key)) {
                    donationEntity.setHospital(key);
                    donationEntity.setCity(value);
                    return;
                }
            });
        }

        if (donationEntity.getCity() != null) {
            donationEntity.setContact(tweet.getUser().getScreenName());
            donationEntity.setTweetId(tweet.getId());
            donationRepository.save(donationEntity);
        }
    }
}
