package com.github.kojotak.bassbook.claude.repository;

import com.github.kojotak.bassbook.claude.entity.YVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YVideoRepository extends JpaRepository<YVideo, Long> {
    
    @Query("SELECT v FROM YVideo v JOIN FETCH v.song s JOIN FETCH s.artist JOIN FETCH v.channel JOIN FETCH v.instrument ORDER BY s.name")
    List<YVideo> findAllOrderBySongName();
}
