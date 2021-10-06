package com.ssafy.checklist.domain.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "filter")
public class Filter {

    @Id
    String category;

    String condition;
}
