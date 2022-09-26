package com.portfolio.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
public class EducationRequest {

    @NotBlank
    private String institute;

    @NotBlank
    private String title;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isPresent;

    @NotNull
    private Long personaId;

}
