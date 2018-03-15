package com.vakses.model.resource;

import lombok.Data;

/**
 * Created by veraxmedax on 15/03/2018.
 */
@Data
public class DonationResource {
    private long id;
    private String hospital;
    private String city;
    private String contact;
    private String bloodGroup;
}
