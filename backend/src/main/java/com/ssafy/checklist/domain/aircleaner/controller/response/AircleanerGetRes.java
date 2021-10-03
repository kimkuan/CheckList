package com.ssafy.checklist.domain.aircleaner.controller.response;

import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class AircleanerGetRes {
    @ApiModelProperty
    @NotNull
    Long pcode;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    Long price;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    String target;      // 사용목적

    @ApiModelProperty
    String area;        // 청정면적

    @ApiModelProperty
    String power;       // 필터등급

    @ApiModelProperty
    String spec;        // 전체스펙

    public static AircleanerGetRes from(Aircleaner aircleaner) {
        return builder()
                .pcode(aircleaner.getPcode())
                .name(aircleaner.getName())
                .brand(aircleaner.getBrand())
                .price(aircleaner.getPrice())
                .img(aircleaner.getImg())
                .target(aircleaner.getTarget())
                .area(aircleaner.getArea())
                .power(aircleaner.getPower())
                .spec(aircleaner.getSpec())
                .build();
    }
}