package com.ssafy.checklist.domain.coffeemachine.controller.response;

import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoffeemachineGetRes {
    @ApiModelProperty
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
    String heatTime;

    @ApiModelProperty
    String waterVolume;

    @ApiModelProperty
    String spec;

    @ApiModelProperty
    long pricePoint;

    @ApiModelProperty
    long brewingPoint;

    @ApiModelProperty
    long waterVolumePoint;

    @ApiModelProperty
    long conveniencePoint;

    public static CoffeemachineGetRes of(Coffeemachine coffeemachine, CoffeemachinePerformance coffeemachinePerformance) {
        CoffeemachineGetRes res = new CoffeemachineGetRes();
        res.setPcode(coffeemachine.getPcode());
        res.setBrand(coffeemachine.getBrand());
        res.setPrice(coffeemachine.getPrice());
        res.setImg(coffeemachine.getImg());
        res.setPressure(coffeemachine.getPressure());
        res.setHeatTime(coffeemachine.getHeatTime());
        res.setWaterVolume(coffeemachine.getWaterVolume());
        res.setSpec(coffeemachine.getSpec());
        res.setPricePoint(coffeemachinePerformance.getPrice());
        res.setBrewingPoint(coffeemachinePerformance.getBrewing());
        res.setWaterVolumePoint(coffeemachinePerformance.getWaterVolume());
        res.setConveniencePoint(coffeemachinePerformance.getConvenience());

        return res;
    }
}
