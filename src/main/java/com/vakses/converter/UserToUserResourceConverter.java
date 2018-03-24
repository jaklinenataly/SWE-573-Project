package com.vakses.converter;

import com.vakses.model.entity.User;
import com.vakses.model.resource.UserResource;
import org.springframework.core.convert.converter.Converter;

public class UserToUserResourceConverter implements Converter<User, UserResource> {
    @Override
    public UserResource convert(User source) {
        return UserResource.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .roles(source.getRoles())
                .subscriptions(source.getSubscriptions())
                .build();
    }
}