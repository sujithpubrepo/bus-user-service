package com.user.bususerservice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userid", nullable = false, length = 10)
    private String userid;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "walletamount", nullable = false)
    private Double walletamount;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

}