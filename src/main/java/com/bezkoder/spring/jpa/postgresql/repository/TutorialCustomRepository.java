package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TutorialCustomRepository {

  Page<Tutorial> customFindByPublished(boolean published, Pageable pageable);
}
