package com.ssafy.checklist.domain.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "low_price_info", schema = "checklist")
public class LowPriceInfo {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "pcode")
    private Long pcode;

    @Column(name = "site")
    private String site;

    @Column(name = "price")
    private Long price;

    @Column(name = "link")
    private String link;

    @Column(name = "img")
    private String img;
}
