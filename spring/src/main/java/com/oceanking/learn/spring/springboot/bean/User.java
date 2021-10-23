package com.oceanking.learn.spring.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String sex;
    private Date birthday;
}
