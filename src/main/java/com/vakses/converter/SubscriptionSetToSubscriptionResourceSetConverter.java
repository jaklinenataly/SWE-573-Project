package com.vakses.converter;

import com.vakses.model.entity.Subscription;
import com.vakses.model.resource.SubscriptionResource;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by veraxmedax on 11/04/2018.
 */
public class SubscriptionSetToSubscriptionResourceSetConverter
        implements Converter<Set<Subscription>, Set<SubscriptionResource>> {
    @Override
    public Set<SubscriptionResource> convert(Set<Subscription> source) {
        Set<SubscriptionResource> subscriptionResources = new HashSet<>();
        source.forEach(subscription -> {
            SubscriptionResource subscriptionResource = new SubscriptionResource();
            subscriptionResource.setId(subscription.getId());
            subscriptionResource.setBloodGroupType(subscription.getBloodGroupType());
            subscriptionResource.setLocation(subscription.getLocation());
            subscriptionResources.add(subscriptionResource);
        });
        return subscriptionResources;
    }
}
