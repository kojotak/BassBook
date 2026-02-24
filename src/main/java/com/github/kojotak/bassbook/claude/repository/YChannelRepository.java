package com.github.kojotak.bassbook.claude.repository;

import com.github.kojotak.bassbook.claude.entity.YChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YChannelRepository extends JpaRepository<YChannel, Long> {
    Optional<YChannel> findByName(String name);
}
