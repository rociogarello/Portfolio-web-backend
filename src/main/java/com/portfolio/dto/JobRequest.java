package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobRequest {

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isPresent;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String jobRole;

    @NotEmpty
    private String jobDescription;

    @NotNull
    private Long personaId;

}
