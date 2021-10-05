package com.ssafy.checklist.domain.foodprocessor.controller.response;

import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@Builder
public class FoodprocessorGetRes {
    Long pcode;

    String name;

    String brand;

    String img;

    Long price;

    String processType;

    String processTime;

    String decrease;

    String size;

    String sound;

    String spec;

    public static FoodprocessorGetRes of(Foodprocessor foodprocessor) {
        return FoodprocessorGetRes.builder()
                .pcode(foodprocessor.getPcode())
                .name(foodprocessor.getName())
                .brand(foodprocessor.getBrand())
                .img(foodprocessor.getImg())
                .price(foodprocessor.getPrice())
                .processType(foodprocessor.getProcessType())
                .processTime(foodprocessor.getProcessTime())
                .decrease(foodprocessor.getDecrease())
                .size(foodprocessor.getSize())
                .sound(foodprocessor.getSound())
                .spec(foodprocessor.getSpec())
                .build();
    }

}
