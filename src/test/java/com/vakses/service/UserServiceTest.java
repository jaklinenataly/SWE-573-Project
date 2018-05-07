package com.vakses.service;

import com.vakses.exception.UserExistsException;
import com.vakses.exception.UserNotFoundException;
import com.vakses.model.dto.UserDto;
import com.vakses.model.entity.User;
import com.vakses.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by veraxmedax on 06/05/2018.
 */
public class UserServiceTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService underTest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindUser() {
        User user = createUser();
        when(userRepositoryMock.findOne("1")).thenReturn(user);

        User userInRepository = underTest.findUserById("1");
        assertEquals(user, userInRepository);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldUserNotFoundExceptionWhenUserIsNotValid() {
        when(userRepositoryMock.findOne("2")).thenReturn(null);

        underTest.findUserById("2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenUserDtoIsNull() {
        underTest.createUser(null);
    }

    @Test(expected = UserExistsException.class)
    public void shouldThrowUserExistsExceptionWhenUserIsInRepository() {
        User user = createUser();
        UserDto userDto = createUserDto();
        when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(user);
        underTest.createUser(userDto);
    }

    private User createUser() {
        User user = new User();
        user.setId("1");
        user.setPassword("Test123*");
        user.setUsername("test245");
        user.setEmail("test@test.com");
        user.setRoles(Collections.singleton("USER"));
        user.setSubscriptions(new HashSet<>());
        return user;
    }


    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setPassword("Test123*");
        userDto.setUsername("test245");
        userDto.setEmail("test@test.com");
        return userDto;
    }
}
