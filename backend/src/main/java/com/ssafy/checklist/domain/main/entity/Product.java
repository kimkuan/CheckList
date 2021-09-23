package com.ssafy.checklist.domain.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Product {

    @Id
    String id;

    String name;

    String modelName;

    String brand;

    String imageUrl;

    int price;

    double score;
}
