package com.vakses.service;

import com.vakses.model.data.BloodGroup;
import com.vakses.model.data.Hospitals;
import com.vakses.model.dto.DonationDto;
import com.vakses.model.entity.DonationEntity;
import com.vakses.model.resource.DonationResource;
import com.vakses.repository.DonationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Slf4j
@Service
public class DonationStoreService {
    private BloodGroup bloodGroup;
    private Hospitals hospitals;
    private DonationRepository donationRepository;
    private ConfigurableConversionService conversionService;

    @Autowired
    public DonationStoreService(BloodGroup bloodGroup, Hospitals hospitals, DonationRepository donationRepository, ConfigurableConversionService conversionService) {
        this.bloodGroup = bloodGroup;
        this.hospitals = hospitals;
        this.donationRepository = donationRepository;
        this.conversionService = conversionService;
    }

    public boolean parseAndStore(Tweet tweet) {
        DonationEntity storedEntity = donationRepository.findByTweetId(tweet.getId());

        if (storedEntity != null) {
            log.info("Donation with tweetId: {} is already existed.", tweet.getId());
            return false;
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
            log.info("Donation with id: {} and tweet id: {} stored successfully.",
                    donationEntity.getId(), donationEntity.getTweetId());
            return true;
        }
        return false;
    }

    public List<DonationResource> getAllDonationRequests() {
        List<DonationEntity> donationEntities = donationRepository.findAll();
        List<DonationResource> donationResources = new ArrayList<>();

        donationEntities.stream().forEach(donationEntity -> {
            donationResources.add(conversionService.convert(donationEntity, DonationResource.class));
        });
        return donationResources;
    }

    public DonationResource getAllDonationRequestsById(long id) {
        DonationEntity donationEntity = donationRepository.findOne(id);
        return conversionService.convert(donationEntity, DonationResource.class);
    }

    public DonationResource createDonationRequest(DonationDto donationDto) {
        validateDonationDto(donationDto);
        DonationEntity donationEntity = conversionService.convert(donationDto, DonationEntity.class);
        DonationEntity storedEntity = donationRepository.save(donationEntity);
        return conversionService.convert(storedEntity, DonationResource.class);
    }

    private void validateDonationDto(DonationDto donationDto) {
        if (!bloodGroup.getTypes().contains(donationDto.getBloodGroup())) {
            throw new IllegalArgumentException("Invalid Blood Group Type");
        }
    }
}
