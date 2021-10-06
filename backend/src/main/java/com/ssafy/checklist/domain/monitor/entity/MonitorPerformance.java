package com.ssafy.checklist.domain.monitor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "monitor_performance")
public class MonitorPerformance {

    @Id
    long pcode;

    int price;

    int size;

    int pixel;

    @Column(name="screen_change")
    int screenChange;

    int convenience;
}
