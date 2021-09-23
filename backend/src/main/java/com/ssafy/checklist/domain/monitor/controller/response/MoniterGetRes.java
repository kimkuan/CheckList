package com.ssafy.checklist.domain.monitor.controller.response;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoniterGetRes {

    @ApiModelProperty
    @NotNull
    String id;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String modelNamel;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    int price;

}
