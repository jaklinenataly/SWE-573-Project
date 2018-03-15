package com.vakses.converter;

import com.vakses.model.entity.DonationEntity;
import com.vakses.model.resource.DonationResource;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by veraxmedax on 15/03/2018.
 */
public class DonationEntityToDonationEntityResourceConverter implements Converter<DonationEntity, DonationResource> {

    @Override
    public DonationResource convert(DonationEntity source) {
        if (source == null) {
            return null;
        }

        final DonationResource donationResource = new DonationResource();
        donationResource.setId(source.getId());
        donationResource.setContact(source.getContact());
        donationResource.setCity(source.getCity());
        donationResource.setHospital(source.getHospital());
        donationResource.setBloodGroup(source.getBloodGroup());
        return donationResource;
    }
}
