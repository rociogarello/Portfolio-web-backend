package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({
        "id",
        "title",
        "about",
        "createdAt",
        "updatedAt",
        "image"
})
public class AboutResponse {

    private Long id;

    private byte[] image;

    private String title;

    private String about;

}
