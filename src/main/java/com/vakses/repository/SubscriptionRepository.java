package com.vakses.repository;

import com.vakses.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by veraxmedax on 24/03/2018.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    public List<Subscription> findByBloodGroupTypeAndLocation(final String bloodGroupType, final String location);

    public Subscription findById(long id);
}
