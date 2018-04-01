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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.vakses.util.Constants.USER_BASE_URL;

@RestController
@RequestMapping(value = USER_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;
    private ConversionService conversionService;

    @Autowired
    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> register(@Valid @RequestBody UserDto userDto) throws UserExistsException {
        User user = userService.createUser(userDto);
        UserResource userResource = conversionService.convert(user, UserResource.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResource);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('user.r')")
    public ResponseEntity<UserResource> findUserById(@PathVariable("userId") @NotEmpty String userId, Principal principal) {
        User user = userService.findUserById(userId);
        UserResource userResource = conversionService.convert(user, UserResource.class);
        return ResponseEntity.ok(userResource);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('user.r')")
    public ResponseEntity<List<UserResource>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping(value = "/{userId}")
    //@PreAuthorize("#hasAuthority('admin.d')")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}