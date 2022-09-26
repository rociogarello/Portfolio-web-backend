package com.portfolio.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Persona extends AbstractEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToOne(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL)
    private About about;

    @Builder.Default
    @OneToMany(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Contact> contactList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Technology> technologyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Job> jobList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Project> projectList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Education> educationList = new ArrayList<>();

    public void addAbout(About about) {
        this.about = about;
        about.setPersona(this);
    }

    public void addContact(Contact contact) {
        this.contactList.add(contact);
        contact.setPersona(this);
    }

    public void addTechnology(Technology technology) {
        this.technologyList.add(technology);
        technology.setPersona(this);
    }

    public void addJob(Job job) {
        this.jobList.add(job);
        job.setPersona(this);
    }

    public void addProject(Project project) {
        this.projectList.add(project);
        project.setPersona(this);
    }

    public void addEducation(Education education) {
        this.educationList.add(education);
        education.setPersona(this);
    }

}
