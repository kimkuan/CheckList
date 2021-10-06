package com.ssafy.checklist.domain.foodprocessor.controller.response;

import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.ssafy.checklist.domain.foodprocessor.entity.FoodprocessorPerformance;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class FoodprocessorInfoGetRes {

    @ApiModelProperty
    @NotNull
    long pcode;

    @ApiModelProperty
    @NotNull
    String name;

    @ApiModelProperty
    String brand;

    @ApiModelProperty
    String img;

    @ApiModelProperty
    long price;

    @ApiModelProperty
    int pricePoint;

    @ApiModelProperty
    int performancePoint;

    @ApiModelProperty
    int managementPoint;

    @ApiModelProperty
    int processingPoint;

    @ApiModelProperty
    int conveniencePoint;

    public static List<FoodprocessorInfoGetRes> of(List<Foodprocessor> foodprocessorsList, List<FoodprocessorPerformance> fpList) {
        List<FoodprocessorInfoGetRes> foodprocessorInfoGetResList = new LinkedList<>();

//        Map<String, Object> monitorMap = new HashMap<String, Object>();
//        monitorMap.put(monitorList.get(0).getPcode()+"", monitorList.get(0));


        for(int i=0; i<foodprocessorsList.size(); i++) {
            FoodprocessorInfoGetRes foodprocessorInfoGetRes = new FoodprocessorInfoGetRes();

            foodprocessorInfoGetRes.setPcode(foodprocessorsList.get(i).getPcode());
            foodprocessorInfoGetRes.setName(foodprocessorsList.get(i).getName());
            foodprocessorInfoGetRes.setPrice(foodprocessorsList.get(i).getPrice());
            foodprocessorInfoGetRes.setBrand(foodprocessorsList.get(i).getBrand());
            foodprocessorInfoGetRes.setImg(foodprocessorsList.get(i).getImg());
            foodprocessorInfoGetRes.setPricePoint(fpList.get(i).getPrice());
            foodprocessorInfoGetRes.setPerformancePoint(fpList.get(i).getPerformance());
            foodprocessorInfoGetRes.setManagementPoint(fpList.get(i).getManagement());
            foodprocessorInfoGetRes.setProcessingPoint(fpList.get(i).getProcessing());
            foodprocessorInfoGetRes.setConveniencePoint(fpList.get(i).getConvenience());

            foodprocessorInfoGetResList.add(foodprocessorInfoGetRes);
        }

        return foodprocessorInfoGetResList;
    }
}
