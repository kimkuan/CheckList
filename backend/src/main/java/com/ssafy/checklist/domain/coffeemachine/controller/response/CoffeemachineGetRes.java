package com.ssafy.checklist.domain.coffeemachine.controller.response;

import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoffeemachineGetRes {
    @ApiModelProperty
    @NotNull
    Long pcode;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    long price;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    String pressure;

    @ApiModelProperty
    String heat_time;

    @ApiModelProperty
    String water_volume;

    @ApiModelProperty
    String spec;
}
