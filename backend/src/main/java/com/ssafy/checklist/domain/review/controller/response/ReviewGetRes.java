package com.ssafy.checklist.domain.review.controller.response;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class ReviewGetRes {

    @ApiModelProperty
    @NotNull
    String id;

    @ApiModelProperty
    String category;

    @ApiModelProperty
    String productId;

    @ApiModelProperty
    int score;

    @ApiModelProperty
    String time;

    @ApiModelProperty
    String site;

    @ApiModelProperty
    String writer;

    @ApiModelProperty
    String content;

}

