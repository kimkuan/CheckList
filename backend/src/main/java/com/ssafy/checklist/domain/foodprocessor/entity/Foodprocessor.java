package com.ssafy.checklist.domain.foodprocessor.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "foodprocessor_product", schema = "checklist")
public class Foodprocessor {

    @Id
    @Column(name = "pcode")
    Long pcode;

    @NotNull
    @Column(name = "name")
    String name;

    @Column(name = "brand")
    String brand;

    @Column(name = "img")
    String img;

    @Column(name = "price")
    Long price;

    @Column(name = "process_type")
    String processType;

    @Column(name = "process_time")
    String processTime;

    @Column(name = "decrease")
    String decrease;

    @Column(name = "size")
    String size;

    @Column(name = "sound")
    String sound;

    @Column(name = "spec")
    String spec;

}
