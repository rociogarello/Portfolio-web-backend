package com.portfolio.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Data
@Entity
@NoArgsConstructor
public class Project extends AbstractEntity {

    private String projectName;

    private String description;

    private String site;

    private String image;

    @Builder.Default
    @ManyToMany
    private List<Technology> technologyList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Persona persona;

}
