package com.ssafy.checklist.domain.airfryer.controller.response;

import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.entity.AirfryerPerformance;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class AirfryerGetRes {

    @ApiModelProperty
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
    String type;

    @ApiModelProperty
    String volume;

    @ApiModelProperty
    String power;

    @ApiModelProperty
    String size;

    @ApiModelProperty
    String spec;

    @ApiModelProperty
    long pricePoint;

    @ApiModelProperty
    long volumePoint;

    @ApiModelProperty
    long cookPoint;

    @ApiModelProperty
    long controlPoint;

    @ApiModelProperty
    long managementPoint;

    @ApiModelProperty
    long conveniencePoint;

    public static AirfryerGetRes of (Airfryer airfryer, AirfryerPerformance airfryerPerformance){
        AirfryerGetRes res = new AirfryerGetRes();
        res.setPcode(airfryer.getPcode());
        res.setName(airfryer.getName());
        res.setBrand(airfryer.getBrand());
        res.setPrice(airfryer.getPrice());
        res.setImg(airfryer.getImg());
        res.setType(airfryer.getType());
        res.setVolume(airfryer.getVolume());
        res.setPower(airfryer.getPower());
        res.setSize(airfryer.getSize());
        res.setSpec(airfryer.getSpec());

        res.setPricePoint(airfryerPerformance.getPrice());
        res.setVolumePoint(airfryerPerformance.getVolume());
        res.setCookPoint(airfryerPerformance.getCook());
        res.setControlPoint(airfryerPerformance.getControl());
        res.setManagementPoint(airfryerPerformance.getManagement());
        res.setConveniencePoint(airfryerPerformance.getConvenience());

        return res;
    }

}
