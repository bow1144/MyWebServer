package com.test.login;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ACT_PWD {
    @Id
    private String account;
    private String password;
}
