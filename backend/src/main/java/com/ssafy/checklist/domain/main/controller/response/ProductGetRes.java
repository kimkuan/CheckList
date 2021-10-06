package com.ssafy.checklist.domain.main.controller.response;

import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGetRes {
    @ApiModelProperty
    @NotNull
    long pcode;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    long price;

    public static ProductGetRes of(Monitor monitor) {
        ProductGetRes product = new ProductGetRes();

        product.setPcode(monitor.getPcode());
        product.setName(monitor.getName());
        product.setBrand(monitor.getBrand());
        product.setImg(monitor.getImg());
        product.setPrice(monitor.getPrice());

        return product;
    }

    public static ProductGetRes of(Airfryer airfryer) {
        ProductGetRes product = new ProductGetRes();

        product.setPcode(airfryer.getPcode());
        product.setName(airfryer.getName());
        product.setBrand(airfryer.getBrand());
        product.setImg(airfryer.getImg());
        product.setPrice(airfryer.getPrice());

        return product;
    }

    public static ProductGetRes of(Aircleaner aircleaner) {
        ProductGetRes product = new ProductGetRes();

        product.setPcode(aircleaner.getPcode());
        product.setName(aircleaner.getName());
        product.setBrand(aircleaner.getBrand());
        product.setImg(aircleaner.getImg());
        product.setPrice(aircleaner.getPrice());

        return product;
    }

    public static ProductGetRes of(Coffeemachine coffeemachine) {
        ProductGetRes product = new ProductGetRes();

        product.setPcode(coffeemachine.getPcode());
        product.setName(coffeemachine.getName());
        product.setBrand(coffeemachine.getBrand());
        product.setImg(coffeemachine.getImg());
        product.setPrice(coffeemachine.getPrice());

        return product;
    }

    public static ProductGetRes of(Foodprocessor foodprocessor) {
        ProductGetRes product = new ProductGetRes();

        product.setPcode(foodprocessor.getPcode());
        product.setName(foodprocessor.getName());
        product.setBrand(foodprocessor.getBrand());
        product.setImg(foodprocessor.getImg());
        product.setPrice(foodprocessor.getPrice());

        return product;
    }

}
