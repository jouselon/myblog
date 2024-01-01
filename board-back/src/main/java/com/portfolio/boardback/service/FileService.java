package com.portfolio.boardback.service;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

public interface FileService {

  String upload(MultipartFile file);
  Resource getImage(String fileName);

}
