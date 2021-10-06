package com.ssafy.checklist.domain.airfryer.controller.response;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirfryerInfoGetRes {

    @ApiModelProperty
    AirfryerGetRes airfryerGetRes;

    // 최저가 사이트 정보
    @ApiModelProperty
    List<LowPriceInfo> lowPriceInfoList;

    public static AirfryerInfoGetRes of(AirfryerGetRes airfryerGetRes, List<LowPriceInfo> lowPriceInfoList) {
        AirfryerInfoGetRes res = new AirfryerInfoGetRes();
        res.setAirfryerGetRes(airfryerGetRes);
        res.setLowPriceInfoList(lowPriceInfoList);
        return res;
    }
}
