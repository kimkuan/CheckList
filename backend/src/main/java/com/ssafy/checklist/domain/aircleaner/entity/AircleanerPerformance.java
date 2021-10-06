package com.ssafy.checklist.domain.aircleaner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "aircleaner_performance")
public class AircleanerPerformance {

    @Id
    long pcode;

    int price;

    int area;

    int sensor;

    int convenience;
}
