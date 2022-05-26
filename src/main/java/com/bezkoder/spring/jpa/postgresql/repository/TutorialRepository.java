package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.dto.TutorialDTO;
import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>, TutorialCustomRepository {
  List<Tutorial> findByPublished(boolean published);

  @Query(name = "activeGrabngoByCity")
  List<Tutorial> findAll1();

//  @SqlFromResource(path = "META-INF/sql/findByPublished.sql")
  List<Tutorial> findByTitleContaining(String title);

  Page<Tutorial> findByPublished(boolean published, Pageable pageable);
}
