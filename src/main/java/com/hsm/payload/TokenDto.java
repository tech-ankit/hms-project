package com.hsm.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDto {
    private String token;
    private String type;
}
