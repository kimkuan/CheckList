package com.ssafy.checklist.domain.aircleaner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Aircleaner {
    @Id
    Long pcode;
    String name;
    String brand;
    Long price;
    String img;
    String target;      // 사용목적
    String area;        // 청정면적
    String power;       // 필터등급
    String spec;        // 전체스펙
}
