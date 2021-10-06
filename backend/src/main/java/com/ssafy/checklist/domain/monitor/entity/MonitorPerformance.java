package com.ssafy.checklist.domain.monitor.entity;

import lombok.Getter;
import lombok.Setter;

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

    int screenChange;

    int convenience;
}
