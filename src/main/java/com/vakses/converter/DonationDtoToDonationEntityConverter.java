package com.vakses.converter;

import com.vakses.model.dto.DonationDto;
import com.vakses.model.entity.DonationEntity;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by veraxmedax on 15/03/2018.
 */
public class DonationDtoToDonationEntityConverter implements Converter<DonationDto, DonationEntity> {

    @Override
    public DonationEntity convert(DonationDto source) {
        if (source == null) {
            return null;
        }

        final DonationEntity donationEntity = new DonationEntity();
        donationEntity.setContact(source.getContact());
        donationEntity.setCity(source.getCity());
        donationEntity.setHospital(source.getHospital());
        donationEntity.setBloodGroup(source.getBloodGroup());
        return donationEntity;
    }
}
