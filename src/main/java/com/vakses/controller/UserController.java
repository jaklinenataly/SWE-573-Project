package com.vakses.controller;

import com.vakses.exception.UserExistsException;
import com.vakses.model.dto.UserDto;
import com.vakses.model.entity.User;
import com.vakses.model.resource.UserResource;
import com.vakses.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.vakses.util.Constants.USER_BASE_URL;

@RestController
@RequestMapping(value = USER_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> register(@Valid @RequestBody UserDto userDto) throws UserExistsException {
        User user = userService.createUser(userDto);
        UserResource userResource = conversionService.convert(user, UserResource.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResource);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user.r')")
    public ResponseEntity<User> findUserById(@PathVariable("userId") @NotEmpty String userId, Principal principal) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user.r')")
    public ResponseEntity<List<UserResource>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasAuthority('admin.d')")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}