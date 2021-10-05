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

    @Id
    Long pcode;

    String name;

    String brand;

    int price;

    String img;

    String pressure;

    String heat_time;

    String water_volume;

    String spec;
}
