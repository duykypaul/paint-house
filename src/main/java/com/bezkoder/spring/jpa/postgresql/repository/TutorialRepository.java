package com.bezkoder.spring.jpa.postgresql.repository;

import java.util.List;

import com.bezkoder.spring.jpa.postgresql.sqlfileprocessor.SqlFromResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>, TutorialCustomRepository {
  List<Tutorial> findByPublished(boolean published);

  @SqlFromResource(path = "META-INF/sql/findByPublished.sql")
  List<Tutorial> findByTitleContaining(String title);

  Page<Tutorial> findByPublished(boolean published, Pageable pageable);
}
