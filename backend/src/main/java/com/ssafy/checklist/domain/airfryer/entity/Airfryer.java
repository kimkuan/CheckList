package com.ssafy.checklist.domain.airfryer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="airfryer_product", schema="checklist")
public class Airfryer {

    @Column(name = "pcode")
    @Id
    long pcode;

    @Column(name = "name")
    String name;

    @Column(name = "brand")
    String brand;

    @Column(name = "price")
    long price;

    @Column(name = "img")
    String img;

    @Column(name = "type")
    String type;

    @Column(name = "volume")
    String volume;

    @Column(name = "power")
    String power;

    @Column(name = "size")
    String size;

    @Column(name = "spec")
    String spec;
}
