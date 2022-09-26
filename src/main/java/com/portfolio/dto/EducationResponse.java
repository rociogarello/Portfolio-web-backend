package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationResponse {

    private Long id;

    private String institute;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

}
