package com.portfolio.boardback.repository;

import com.portfolio.boardback.entity.FavoriteEntity;
import com.portfolio.boardback.entity.primaryKey.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {
}
