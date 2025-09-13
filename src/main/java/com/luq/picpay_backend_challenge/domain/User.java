package com.luq.picpay_backend_challenge.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String mail;
    private String password;
    private BigDecimal balance;

    public User(String fullName, String mail, String document, String password, BigDecimal balance){
        this.fullName = fullName;
        this.mail = mail;
        this.document = document;
        this.password = password;
        this.balance = balance;
    }
}
