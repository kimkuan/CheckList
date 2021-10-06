package com.ssafy.checklist.domain.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "click_count")
public class ClickCount {

    long pcode;

    String category;

    long count;
}
