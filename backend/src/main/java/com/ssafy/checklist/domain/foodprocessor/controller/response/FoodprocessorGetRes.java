package com.ssafy.checklist.domain.foodprocessor.controller.response;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodprocessorGetRes {
    @ApiModelProperty(required = true)
    @NotNull
    String id;

    @ApiModelProperty(required = true)
    String name;

    @ApiModelProperty(required = true)
    String modelName;

    @ApiModelProperty(required = true)
    String brand;

    @ApiModelProperty(required = true)
    int price;
}