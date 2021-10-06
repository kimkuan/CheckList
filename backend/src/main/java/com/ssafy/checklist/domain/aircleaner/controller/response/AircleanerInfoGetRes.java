package com.ssafy.checklist.domain.aircleaner.controller.response;

import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.entity.AircleanerPerformance;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AircleanerInfoGetRes {
    @ApiModelProperty
    @NotNull
    long pcode;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    long price;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    String type;      // 필터타입

    @ApiModelProperty
    String area;        // 청정면적

    @ApiModelProperty
    String dust;       // 먼지필터여부

    @ApiModelProperty
    String spec;        // 전체스펙

    @ApiModelProperty
    long pricePoint;

    @ApiModelProperty
    long areaPoint;

    @ApiModelProperty
    long sensorPoint;

    @ApiModelProperty
    long conveniencePoint;

    @ApiModelProperty
    List<LowPriceInfo> lowPriceInfoList;
    
    public static AircleanerInfoGetRes from(Aircleaner aircleaner,
                                            AircleanerPerformance aircleanerPerformance,
                                            List<LowPriceInfo> lowPriceInfoList) {
        return builder()
                .pcode(aircleaner.getPcode())
                .name(aircleaner.getName())
                .brand(aircleaner.getBrand())
                .price(aircleaner.getPrice())
                .img(aircleaner.getImg())
                .type(aircleaner.getType())
                .area(aircleaner.getArea())
                .dust(aircleaner.getDust())
                .spec(aircleaner.getSpec())
                .areaPoint(aircleanerPerformance.getArea())
                .sensorPoint(aircleanerPerformance.getSensor())
                .conveniencePoint(aircleanerPerformance.getConvenience())
                .lowPriceInfoList(lowPriceInfoList)
                .build();
    }
}