package com.github.kojotak.bassbook.claude.repository;

import com.github.kojotak.bassbook.claude.entity.Tuning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuningRepository extends JpaRepository<Tuning, Long> {
    List<Tuning> findByInstrumentIdOrderByIdAsc(Long instrumentId);
}
