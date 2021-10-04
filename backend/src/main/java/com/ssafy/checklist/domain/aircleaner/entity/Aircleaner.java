package com.ssafy.checklist.domain.aircleaner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="Aircleaner_product")
public class Aircleaner {
    @Id
    Long pcode;
    String name;
    String brand;
    Long price;
    String img;
    String type;        // 필터타입
    String area;        // 청정면적
    String dust;        // 먼지필터여부
    String spec;        // 전체스펙
}
