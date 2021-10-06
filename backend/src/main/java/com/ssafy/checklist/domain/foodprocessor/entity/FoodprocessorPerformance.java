package com.ssafy.checklist.domain.foodprocessor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "foodprocessor_performance")
public class FoodprocessorPerformance {

    @Id
    long pcode;

    int price;

    int performance;

    int management;

    int processing;

    int convenience;
}
