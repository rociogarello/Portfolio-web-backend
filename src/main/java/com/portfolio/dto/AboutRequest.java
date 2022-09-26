package com.portfolio.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AboutRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String about;

    @NotNull
    private Long personaId;

}
