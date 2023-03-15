package com.tqt.WebBasic.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Generated
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail")
    private String mail;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "registrationdate")
    private String registrationdate;
    private Integer role_id;

    private String image_url;
}
