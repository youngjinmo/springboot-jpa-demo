package com.inflearn.hellospringboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Member {
    private Long id;
    private String name;
}
