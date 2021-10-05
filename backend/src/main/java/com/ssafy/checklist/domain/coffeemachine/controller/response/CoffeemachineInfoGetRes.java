package com.ssafy.checklist.domain.coffeemachine.controller.response;

import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoffeemachineInfoGetRes {
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

    // 최저가 사이트 정보
    @ApiModelProperty
    List<LowPriceInfo> lowPriceInfoList;

    public static CoffeemachineInfoGetRes of(Coffeemachine coffeemachine, CoffeemachinePerformance coffeemachinePerformance, List<LowPriceInfo> lowPriceInfoList) {
        CoffeemachineInfoGetRes res = new CoffeemachineInfoGetRes();
        res.setPcode(coffeemachine.getPcode());
        res.setBrand(coffeemachine.getBrand());
        res.setImg(coffeemachine.getImg());
        res.setPrice(coffeemachine.getPrice());
        res.setPressure(coffeemachine.getPressure());
        res.setHeatTime(coffeemachine.getHeatTime());
        res.setWaterVolume(coffeemachine.getWaterVolume());
        res.setSpec(coffeemachine.getSpec());
        res.setPricePoint(coffeemachinePerformance.getPrice());
        res.setBrewingPoint(coffeemachinePerformance.getBrewing());
        res.setWaterVolumePoint(coffeemachinePerformance.getWaterVolume());
        res.setConveniencePoint(coffeemachinePerformance.getConvenience());
        res.setLowPriceInfoList(lowPriceInfoList);

        return res;
    }
}
