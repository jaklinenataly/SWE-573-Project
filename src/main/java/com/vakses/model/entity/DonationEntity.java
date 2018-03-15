package com.vakses.model.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Data
@Entity
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long tweetId;
    private String hospital;
    private String city;
    private String contact;
    private String bloodGroup;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DonationEntity{" +
                "tweetId=" + tweetId +
                ", hospital='" + hospital + '\'' +
                ", city='" + city + '\'' +
                ", contact='" + contact + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
