package com.vakses.repository;

import com.vakses.model.entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {
    DonationEntity findByTweetId(long tweetId);

}
