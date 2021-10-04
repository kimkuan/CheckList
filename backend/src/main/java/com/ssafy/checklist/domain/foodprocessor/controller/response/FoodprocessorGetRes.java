package com.ssafy.checklist.domain.foodprocessor.controller.response;

import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FoodprocessorGetRes {
    Long pcode;

    String name;

    String brand;

    Long price;

    String img;

    String form;

    String volume;

    String processType;

    String spec;

    public static FoodprocessorGetRes of(Foodprocessor foodprocessor) {
        return FoodprocessorGetRes.builder()
                .pcode(foodprocessor.getPcode())
                .name(foodprocessor.getName())
                .brand(foodprocessor.getBrand())
                .price(foodprocessor.getPrice())
                .img(foodprocessor.getImg())
                .form(foodprocessor.getForm())
                .volume(foodprocessor.getVolume())
                .processType(foodprocessor.getProcessType())
                .spec(foodprocessor.getSpec())
                .build();
    }

}
