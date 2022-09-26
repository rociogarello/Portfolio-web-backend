package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Builder.Default
    private List<Long> educationListIds = new ArrayList<>();

    @Builder.Default
    private List<Long> projectListIds = new ArrayList<>();

    private Long aboutId;

    @Builder.Default
    private List<Long> contactListIds = new ArrayList<>();

    @Builder.Default
    private List<Long> jobListIds = new ArrayList<>();

    @Builder.Default
    private List<Long> technologyListIds = new ArrayList<>();

}
