package com.ssafy.checklist.domain.review.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
public class Review {

    @Id
    String id;

    String category;

    String productId;

    int score;

    String time;

    String site;

    String writer;

    // 사진이 여러 개 들어가는 경우 어떻게 처리해야 할까요?
    String photoList; // JSON 형태의 String으로 저장
}
