package com.vakses.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by veraxmedax on 15/03/2018.
 */
@Data
public class DonationDto {
    @NotNull
    private String hospital;
    @NotNull
    private String city;
    @NotNull
    private String contact;
    @NotNull
    private String bloodGroup;
}
