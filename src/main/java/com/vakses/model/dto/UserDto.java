package com.vakses.model.dto;

import com.vakses.util.FieldValidators;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * Created by veraxmedax on 20/03/2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(hidden = true)
    private long userId;

    @Pattern(regexp = FieldValidators.USERNAME, message = "Username is not valid!")
    private String username;

    @Pattern(regexp = FieldValidators.PASSWORD, message = "Password is not valid!")
    private String password;

    @Pattern(regexp = FieldValidators.EMAIL, message = "Email is not valid!")
    private String email;

}