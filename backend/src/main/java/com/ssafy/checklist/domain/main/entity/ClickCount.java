package com.ssafy.checklist.domain.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "click_count")
public class ClickCount {

    @Id
    long pcode;

    String category;

    long count;
}
