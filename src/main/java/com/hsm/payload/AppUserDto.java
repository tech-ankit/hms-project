package com.hsm.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {

    private String username;

    private String email;

    private String name;

    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
