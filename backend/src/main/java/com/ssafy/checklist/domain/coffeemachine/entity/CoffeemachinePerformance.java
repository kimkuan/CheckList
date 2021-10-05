package com.ssafy.checklist.domain.coffeemachine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "coffeemachine_performance", schema = "checklist")
public class CoffeemachinePerformance {

    @Column(name="pcode")
    @Id
    Long pcode;

    @Column(name="price")
    private Long price;

    @Column(name="brewing")
    private Long brewing;           // 추출성능값

    @Column(name="water_volume")
    private Long waterVolume;      // 물통용량성능값

    @Column(name="convenience")
    private Long convenience;       // 부가기능성능값
}
