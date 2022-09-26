package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponse {

    private Long id;

    private String projectName;

    private String description;

    private String site;

    private byte[] image;

    private List<TechnologyResponse> technologyList;

}
