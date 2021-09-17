package com.ssafy.checklist.domain.log.controller.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogBestProductGetRes {

    @ApiModelProperty
    String id;

    @ApiModelProperty
    String modelName;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    int price;

    @ApiModelProperty
    String imageUrl;

}
