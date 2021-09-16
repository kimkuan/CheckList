package com.ssafy.checklist.domain.aircleaner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Aircleaner {
    @Id
    String id;

    String name;

    String modelName;

    String brand;

    int price;
}
