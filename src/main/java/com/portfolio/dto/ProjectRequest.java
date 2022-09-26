package com.portfolio.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class ProjectRequest {

    @NotBlank
    private String projectName;

    @NotBlank
    private String description;

    @NotBlank
    private String site;

    private String image;

    @NotNull
    private Long personaId;

    private List<Long> technologiesId;

}
