package com.vakses.model.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by veraxmedax on 08/03/2018.
 */
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
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
