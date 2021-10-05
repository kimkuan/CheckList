package com.ssafy.checklist.domain.monitor.controller.response;

import com.google.gson.JsonObject;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorGetRes {

    @ApiModelProperty
    @NotNull
    Long pcode;

    @ApiModelProperty
    @NotNull
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    Long price;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    String size;

    @ApiModelProperty
    String pixel;

    @ApiModelProperty
    int hz;

}
