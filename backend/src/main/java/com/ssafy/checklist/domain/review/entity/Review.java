package com.ssafy.checklist.domain.review.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "review", schema = "checklist")
public class Review {

    @Id
    long id;

    String category;

    long pcode;

    String title;

    String content;

    String date;

    int score;

    String site;

    String userId;

    String name;

    String image; // JSON 형태의 String으로 저장
}
