package com.surri.springboot.users.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
@Entity(name="Users")
public class User {
    @Id @GeneratedValue
    private Long id;
    private String createdAt;
    private String username;
    private String password;
}
