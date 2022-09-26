package com.portfolio.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {

    private String username;
    private String password;

}
