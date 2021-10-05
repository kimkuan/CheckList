package com.ssafy.checklist.domain.monitor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "moniter_product", schema = "checklist")
public class Moniter {

    @Id
    String id;

    String name;

    String modelName;

    String brand;

    int price;

    String imageUrl;


}
