package com.portfolio.boardback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {

  @Id
  private int boardNumber;
  private String title;
  private String content;
  private String titleImage;
  private int viewCount;
  private int favoriteCount;
  private int commentCount;
  private String writeDatetime;
  private String writerEmail;
  private String writerNickname;
  private String writerProfileImage;




}
