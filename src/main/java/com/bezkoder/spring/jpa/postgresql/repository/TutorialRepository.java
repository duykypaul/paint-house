package com.bezkoder.spring.jpa.postgresql.repository;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.springframework.data.jpa.repository.Query;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>, TutorialCustomRepository {
  List<Tutorial> findByPublished(boolean published);

  @Query(value = "")
  List<Tutorial> findByTitleContaining(String title);

  Page<Tutorial> findByPublished(boolean published, Pageable pageable);
}
