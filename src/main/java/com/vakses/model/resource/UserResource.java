package com.vakses.model.resource;

import com.vakses.model.entity.Subscription;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by veraxmedax on 20/03/2018.
 */
@Builder
@Getter
@Setter
public class UserResource {
    private String id;
    private String username;
    private String email;
    private Set<String> roles;
    private Set<Subscription> subscriptions;
}