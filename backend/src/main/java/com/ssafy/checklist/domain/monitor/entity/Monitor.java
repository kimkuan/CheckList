package com.ssafy.checklist.domain.monitor.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "monitor_product")
public class Monitor {

    @Id
    long pcode;

    @NotNull
    String name;

    String brand;

    long price;

    String img;

    String size;

    String pixel;

    int hz;

    String spec;

}
