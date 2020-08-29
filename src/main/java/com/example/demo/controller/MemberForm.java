package com.example.demo.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberForm {
    @NotEmpty(message="회원이름은 필수")
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
