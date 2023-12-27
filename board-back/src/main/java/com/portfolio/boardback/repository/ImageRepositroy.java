package com.portfolio.boardback.repository;

import com.portfolio.boardback.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepositroy extends JpaRepository<ImageEntity, Integer> {

}
