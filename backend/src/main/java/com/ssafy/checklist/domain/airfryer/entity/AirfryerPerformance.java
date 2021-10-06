package com.ssafy.checklist.domain.airfryer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "airfryer_performance", schema = "checklist")
public class AirfryerPerformance {

    @Column(name = "pcode")
    @Id
    long pcode;

    @Column(name = "price")
    long price;

    @Column(name = "volume")
    long volume;

    @Column(name = "cook")
    long cook;

    @Column(name = "control")
    long control;

    @Column(name = "management")
    long management;

    @Column(name = "convenience")
    long convenience;

}
