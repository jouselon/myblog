package com.portfolio.boardback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int boardNumber;
  private String title;
  private String contents;
  private String writeDatetime;
  private int viewCount;
  private int commentCount;
  private int favoriteCount;
  private String writerEmail;

  }


