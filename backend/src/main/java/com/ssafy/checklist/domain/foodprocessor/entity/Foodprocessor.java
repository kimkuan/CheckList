package com.ssafy.checklist.domain.foodprocessor.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Foodprocessor {

    @Id
    Long pcode;

    @NotNull
    String name;

    String brand;

    Long price;

    String img;

    String form;

    String volume;

    String processType;

    String spec;

}
