package com.ssafy.checklist.domain.review.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "review_wordcloud", schema = "checklist")
public class ReviewWordCloud {
    @Column(name = "pcode")
    @Id
    private Long pcode;

    @Column(name = "wordcloud")
    String wordcloud;
}
