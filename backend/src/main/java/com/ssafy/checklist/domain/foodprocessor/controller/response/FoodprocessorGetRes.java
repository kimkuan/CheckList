package com.ssafy.checklist.domain.foodprocessor.controller.response;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

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

    List<LowPriceInfo> lowPriceInfoList;

    public static FoodprocessorGetRes of(Foodprocessor foodprocessor, List<LowPriceInfo> lowPriceInfoList) {
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
                .lowPriceInfoList(lowPriceInfoList)
                .build();
    }

}
