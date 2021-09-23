package com.ssafy.checklist.domain.aircleaner.controller.response;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AircleanerGetRes {
    @ApiModelProperty
    @NotNull
    String id;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String modelName;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    String imageUrl;

    @ApiModelProperty
    int price;
}
