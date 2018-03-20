package com.vakses.converter;

import com.vakses.model.dto.UserDto;
import com.vakses.model.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by veraxmedax on 20/03/2018.
 */
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
        return user;
    }
}