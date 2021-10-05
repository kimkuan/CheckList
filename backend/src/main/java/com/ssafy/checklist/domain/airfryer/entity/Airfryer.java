package com.ssafy.checklist.domain.airfryer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="airfryer_product", schema="checklist")
public class Airfryer {
    @Id
    String id;

    String name;

    String modelName;

    String brand;

    String imageUrl;

    int price;
}
