package com.ssafy.checklist.domain.monitor.controller.response;

import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.entity.MonitorPerformance;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MonitorInfoGetRes {

    @ApiModelProperty
    @NotNull
    long pcode;

    @ApiModelProperty
    @NotNull
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    long price;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    int pricePoint;

    @ApiModelProperty
    int sizePoint;

    @ApiModelProperty
    int pixelPoint;

    @ApiModelProperty
    int screenChangePoint;

    @ApiModelProperty
    int conveniencePoint;

    public static List<MonitorInfoGetRes> of(List<Monitor> monitorList, List<MonitorPerformance> mpList) {
        List<MonitorInfoGetRes> monitorInfoGetResList = new LinkedList<>();

//        Map<String, Object> monitorMap = new HashMap<String, Object>();
//        monitorMap.put(monitorList.get(0).getPcode()+"", monitorList.get(0));


        for(int i=0; i<monitorList.size(); i++) {
            MonitorInfoGetRes monitorInfoGetRes = new MonitorInfoGetRes();

            monitorInfoGetRes.setPcode(monitorList.get(i).getPcode());
            monitorInfoGetRes.setName(monitorList.get(i).getName());
            monitorInfoGetRes.setPrice(monitorList.get(i).getPrice());
            monitorInfoGetRes.setBrand(monitorList.get(i).getBrand());
            monitorInfoGetRes.setImg(monitorList.get(i).getImg());
            monitorInfoGetRes.setPricePoint(mpList.get(i).getPrice());
            monitorInfoGetRes.setSizePoint(mpList.get(i).getSize());
            monitorInfoGetRes.setPixelPoint(mpList.get(i).getPixel());
            monitorInfoGetRes.setScreenChangePoint(mpList.get(i).getScreenChange());
            monitorInfoGetRes.setConveniencePoint(mpList.get(i).getConvenience());

            monitorInfoGetResList.add(monitorInfoGetRes);
        }

        return monitorInfoGetResList;
    }
}
