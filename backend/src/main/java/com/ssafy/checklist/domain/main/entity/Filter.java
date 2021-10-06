package com.ssafy.checklist.domain.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "filter")
public class Filter {

    String category;

    String condition;
}
