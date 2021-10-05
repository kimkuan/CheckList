package com.ssafy.checklist.domain.coffeemachine.entity;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "coffeemachine_product", schema = "checklist")
public class Coffeemachine {

    @Column(name = "pcode")
    @Id
    Long pcode;

    @Column(name = "name")
    String name;

    @Column(name = "brand")
    String brand;

    @Column(name = "price")
    Long price;

    @Column(name = "img")
    String img;

    @Column(name = "pressure")
    String pressure;

    @Column(name = "heat_time")
    String heatTime;

    @Column(name = "water_volume")
    String waterVolume;

    @Column(name = "spec")
    String spec;
}
